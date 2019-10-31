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
    public Map<String,Object> save(Integer id){
        Map<String,Object> map = new HashMap<>();
        RuKu ruKu = ruKuService.findById(id);

        Storage storage = new Storage();
        storage.setRuKuDate(new Date());
        storage.setRuKu(ruKu);
        storage.setSaleList(ruKu.getSaleList());
        storage.setState("入库");
        storageService.save(storage);

        saleListService.setState(ruKu.getSaleList().getId(),"入库");

        map.put("success",true);
        return map;
    }

    /**
     * 更新状态
     * @param Ids
     * @param state
     * @return
     */
    @RequestMapping("/updateState")
    public Map<String,Object> updateState(Integer []Ids,String state){
        Map<String,Object> map = new HashMap<>();
        storageService.updateState(Ids,state);
        map.put("success",true);
        return map;
    }

    /**
     * 根据状态查找
     * @param state
     * @return
     */
    @RequestMapping("/findByState")
    public Map<String,Object> findByState(String state){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",storageService.findByState(state));
        return map;
    }

    /**
     * 产品出库
     * @param Ids
     * @return
     */
    @RequestMapping("/chuku")
    public Map<String,Object> chuku(Integer []Ids){
        Map<String,Object> map = new HashMap<>();
        for (int i= 0;i<Ids.length;i++){
            Storage storage = storageService.findById(Ids[i]);
            storage.setState("出库");
            storage.setChuKuDate(new Date());
            storageService.save(storage);
        }
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
    public Map<String, Object> detail(String date) throws ParseException {
        System.out.println(date);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        if (StringUtil.isNotEmpty(date)) {
            map1.put("date", date);
        } else {
            map1.put("date", null);
        }
        map.put("success", true);
        List<Storage> storageList = storageService.detail(map1);
        map.put("rows", storageList);
        return map;
    }

}
