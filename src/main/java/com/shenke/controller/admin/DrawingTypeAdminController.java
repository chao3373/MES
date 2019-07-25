package com.shenke.controller.admin;


import com.shenke.entity.DrawingType;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingService;
import com.shenke.service.DrawingTypeService;
import com.shenke.service.SaleListService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/drawingType")
public class DrawingTypeAdminController {


    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingService drawingService;

    @Resource
    private DrawingTypeService drawingTypeService;

    @Resource
    private SaleListService saleListService;


    @RequestMapping("/addSonDrawing")
    public Map<String,Object> addSonDrawing(String smallIds,Integer id) throws Exception {
        Map<String,Object> map = new HashMap<>();
        String idsStr[] = smallIds.split(",");

        for(int i =0 ;i<idsStr.length;i++){
            DrawingType drawingType = new DrawingType();
            drawingType.setSaleList(saleListService.findById(id));
            drawingType.setDrawing(drawingService.findById(Integer.parseInt(idsStr[i])));
            drawingType.setState("图纸展开");
            drawingTypeService.addSonDrawing(drawingType);
        }

        saleListService.setState(id,"图纸展开");
        map.put("success",true);
        return map;
    }

    /**
     * 根据id修改状态
     * @param id
     * @param state
     */
    @RequestMapping("/setState")
    public void setState(Integer id,String state){
        drawingTypeService.setState(id,state);
    }
}
