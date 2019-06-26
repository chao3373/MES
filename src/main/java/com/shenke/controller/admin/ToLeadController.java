package com.shenke.controller.admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

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

    @RequestMapping("/importFile")
    public Map<String, Object> getExcel(@RequestParam("fileName") MultipartFile file) {// MultipartFile
        // 接收来自表单的File文件，然后进行服务器的上传
        // new HashMap<>();
        System.out.println(file);
        // 文件名
        String fileName = file.getOriginalFilename(); // 获取上传时的文件名

        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 工作区域
        Workbook wb = null;

        // 判断文件后缀名
        if (prefix.equals("xlsx")) {
            try {
                wb = new XSSFWorkbook(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (prefix.equals("xls")) {
            try {
                wb = new HSSFWorkbook(file.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Sheet sheet = wb.getSheetAt(0);

        // 用来存放内容的map
        Map<String, Object> map = new HashMap<String, Object>();

        // 设置实际厚度小数点后面的位数
        DecimalFormat df = new DecimalFormat("0.0000"); // DecimalFormat 数字格式化
        DecimalFormat dd = new DecimalFormat("0.00"); //格式化保留两位小数

        // 用来存放订单商品的集合
        List<SaleList> list = new ArrayList<SaleList>();
        // 循环工作表的行
        for (Row row : sheet) {
            if (row.getRowNum() != 0 && row.getRowNum() != 1
                    && row.getRowNum() != (sheet.getPhysicalNumberOfRows() - 1)) {
                // 创建订单中每个商品的对象
                SaleList saleList = new SaleList();
                // 循环每行中的单元格
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0 || cell.getRowIndex() == 0 || cell.getRowIndex() == 1) {// getColumnIndexOrThrow
                        continue;
                    }
                    switch (cell.getColumnIndex()) {
                        case 1:
                            // map.put("库存组织", getStringCellValue(cell));
                            String kucunzuzhi = getStringCellValue(cell);
                            saleList.setKucunzuzhi(kucunzuzhi);
                            break;
                        case 2:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("行号", getStringCellValue(cell));
                                saleList.setHangHao(getStringCellValue(cell));
                            }
                            break;
                        case 3:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("物料编号", getStringCellValue(cell));
                                saleList.setWuliaoId((int) Double.parseDouble(getStringCellValue(cell)));
                            }
                            break;
                        case 4:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("图纸名称", getStringCellValue(cell));
                                saleList.setTuzhiName(getStringCellValue(cell));
                            }
                            break;
                        case 5:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("图纸编号", df.format(Double.parseDouble(getStringCellValue(cell))));
                                saleList.setTuzhiId(getStringCellValue(cell));
                            }
                            break;
                        case 6:
                            //if(!(StringUtil.isNotEmpty(getStringCellValue(cell)))) {
                                // map.put("数量", getStringCellValue(cell));
                                saleList.setNum((int) Double.parseDouble(getStringCellValue(cell)));
                            //}
                            //break;
                        case 7:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("项目号", getStringCellValue(cell));
                                saleList.setXiangmuId(getStringCellValue(cell));
                            }
                            break;
                        case 8:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("申请单号", getStringCellValue(cell));
                                saleList.setShenqingNumber(getStringCellValue(cell));
                            }
                            break;
                    }
                    String data = getStringCellValue(cell);
                }
                map.put("success", true); // 成功
                map.put("rows", list);
                list.add(saleList);
            }
        }

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
