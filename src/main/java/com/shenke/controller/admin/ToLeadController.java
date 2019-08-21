package com.shenke.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.shenke.entity.Log;
import com.shenke.entity.SaleList;
import com.shenke.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shenke.util.StringUtil;

/**
 * 订单导入Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/toLead")
public class ToLeadController {

    @Resource
    private ToLeadService toLeadService;

    @Resource
    private LogService logService;

    /**
     * 导入订单表格
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/importFile")
    public Map<String, Object> getExcel(@RequestParam("fileName") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Map<String, Object>> maps = reader.readAll();
        for (int i = 0; i < maps.size(); i++) {
            maps.get(i).put("kucunzuzhi", maps.get(i).remove("库存组织"));
            maps.get(i).put("hangHao", maps.get(i).remove("行号"));
            maps.get(i).put("wuliaoId", maps.get(i).remove("物料编号"));
            maps.get(i).put("tuzhiName", maps.get(i).remove("图纸名称"));
            maps.get(i).put("tuzhiId", maps.get(i).remove("图纸编号"));
            maps.get(i).put("num", maps.get(i).remove("数量"));
            maps.get(i).put("xiangmuId", maps.get(i).remove("项目号"));
            maps.get(i).put("shenqingNumber", maps.get(i).remove("申请单号"));
        }
        map.put("success", true);
        map.put("rows", maps);
        logService.save(new Log(Log.SEARCH_ACTION, "导入订单表格"));
        return map;
    }

    // 读取单元格内容 并转为字符串
    private static String getStringCellValue(Cell cell) {

        String strCell;

        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("")) {
            return "";
        }
        return strCell;
    }
}
