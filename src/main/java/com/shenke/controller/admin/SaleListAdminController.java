package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.Drawing;
import com.shenke.entity.SaleList;
import com.shenke.service.DrawingService;
import com.shenke.service.SaleListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/saleList")
public class SaleListAdminController {
    @Resource
    private SaleListService saleListService;

    @Resource
    private DrawingService drawingService;

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
           if(drawingService.findDrawingId(saleList.getTuzhiId())!=null){
               saleList.setCunzai("存在");
           }

        }



        saleListService.save(plgList);
        map.put("success",true);
        System.out.println(map);
        return map;
    }

    /**
     * 查询下单成功的产品信息
     * @return
     */
    @RequestMapping("/xiadanSuccess")
    public Map<String,Object> xiadanSuccess(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.xiadan();
        map.put("success",map);
        return map;
    }
}
