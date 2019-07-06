package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.Drawing;
import com.shenke.entity.DrawingType;
import com.shenke.entity.SaleList;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingTypeService;
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
        map.put("rows",list);
        System.out.println(map);
        return map;
    }

    /**
     * 根据id修改存在状态
     * @param id
     * @return
     */
    @RequestMapping("/addCunZai")
    public Map<String,Object> addCunZai(Integer id){
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        saleListService.addCunZai(id);
        map.put("success",true);
        return map;
    }

    /**
     * 点击大图纸信息显示所包含的小图纸信息
     * @param drawingId
     * @return
     */
    @RequestMapping("/bigSmallDrawing")
    public Map<String,Object> BigSmallDrawing(String drawingId){
        Map<String,Object> map = new HashMap<>();
        Integer id = bigDrawingService.findIdByDrawingId(drawingId);
        List<DrawingType> list = drawingTypeService.findByBigDrawingId(id);
        List<Drawing> list1 = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            list1.add(list.get(i).getDrawing());
        }
        map.put("rows",list1);
        return map;
    }
}
