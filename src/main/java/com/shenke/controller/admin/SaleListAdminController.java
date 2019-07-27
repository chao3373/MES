package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.Drawing;
import com.shenke.entity.DrawingType;
import com.shenke.entity.Log;
import com.shenke.entity.SaleList;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingTypeService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/saleList")
public class SaleListAdminController {
    @Resource
    private SaleListService saleListService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingTypeService drawingTypeService;

    @Resource
    private LogService logService;

    /**
     * 保存添加订单
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(String goodsJson) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<SaleList> plgList = gson.fromJson(goodsJson, new TypeToken<List<SaleList>>() {
        }.getType());

       for(SaleList saleList : plgList){
           saleList.setState("下单");
           if(bigDrawingService.findBigDrawingId(saleList.getTuzhiId())!=null){
               saleList.setCunzai("存在");
           }
        }
        saleListService.save(plgList);
        map.put("success",true);
        logService.save(new Log(Log.ADD_ACTION, "保存添加订单"));
        return map;
    }

    /**
     * 查询状态为下单的产品信息
     * @return
     */
    @RequestMapping("/xiadanSuccess")
    public Map<String,Object> xiadanSuccess(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.xiadan();
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION, "查询状态为下单的商品"));
        return map;
    }

    /**
     * 根据id修改存在状态
     * @param id
     * @return
     */
    @RequestMapping("/addCunZai")
    public Map<String,Object> addCunZai(Integer id){
        Map<String,Object> map = new HashMap<>();
        saleListService.addCunZai(id);
        map.put("success",true);
        logService.save(new Log(Log.SEARCH_ACTION, "根据id修改存在状态"));
        return map;
    }

    /**
     * 点击大图纸信息显示所包含的小图纸信息
     * @return
     */
    @RequestMapping("/bigSmallDrawing")
    public Map<String,Object> BigSmallDrawing(Integer id){
        Map<String,Object> map = new HashMap<>();
        List<DrawingType> list = drawingTypeService.findBySaleListId(id);
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION, "根据大图纸查询小图纸信息"));
        return map;
    }

    /**
     * 设置订单状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/setState")
    public Map<String,Object> setState(Integer id,String state,Double prepareTime){
        Map<String,Object> map = new HashMap<>();
        saleListService.setState(id,state);
        saleListService.setPrepareTime(id,prepareTime);
        map.put("success",true);
        logService.save(new Log(Log.UPDATE_ACTION, "设置订单状态"));
        return map;
    }

    /**
     * 按照状态查询
     * @param state
     * @return
     */
    @RequestMapping("/findByState")
    public Map<String,Object> findByState(String state){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.findByState(state);
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION, "按照订单状态查询订单信息"));
        return map;
    }
}
