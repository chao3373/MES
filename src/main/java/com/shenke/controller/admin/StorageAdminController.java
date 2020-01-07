package com.shenke.controller.admin;

import com.shenke.entity.RuKu;
import com.shenke.entity.Storage;
import com.shenke.service.*;
import com.shenke.util.StringUtil;
import javafx.scene.chart.PieChart;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库Controller
 *
 */
@RestController
@RequestMapping("/admin/storage")
public class StorageAdminController {

    @Resource
    private SaleListService saleListService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingService drawingService;

    @Resource
    private StorageService storageService;

    @Resource
    private RuKuService ruKuService;

    /**
     * 新增入库
     * @param
     * @return
     */
    @RequestMapping("/save")
    public Map<String,Object> save(Integer id,Integer num){
        Map<String,Object> map = new HashMap<>();
        RuKu ruKu = ruKuService.findById(id);

        StringBuffer code = new StringBuffer("FN");
        String outCode = storageService.selectMaxOutCode();
        if(outCode!=null){
            code.append(StringUtil.formatCode(outCode));
        }else{
            code.append("00000001");
        }

        Storage storage = new Storage();
        storage.setFahuoDate(new Date());
        storage.setSaleList(ruKu.getSaleList());
        storage.setNum(num);
        storage.setFahuoNumber(code.toString());
        storageService.save(storage);

        saleListService.setState(ruKu.getSaleList().getId(),"入库");

        map.put("success",true);
        return map;
    }

    /**
     * 按条件查询出库明细表
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/detail")
    public Map<String, Object> detail(String fahuoDate,String fahuoNumber) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        if (StringUtil.isNotEmpty(fahuoDate)) {
            map1.put("date", fahuoDate);
        } else {
            map1.put("date", null);
        }
        map1.put("fahuoNumber",fahuoNumber);
        List<Storage> storageList = storageService.detail(map1);
        map.put("success", true);
        map.put("rows", storageList);
        return map;
    }

}
