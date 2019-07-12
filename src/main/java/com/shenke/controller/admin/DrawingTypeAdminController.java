package com.shenke.controller.admin;


import com.shenke.entity.DrawingType;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingService;
import com.shenke.service.DrawingTypeService;
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


    @RequestMapping("/addSonDrawing")
    public Map<String,Object> addSonDrawing(String smallIds, String bigDrawingId){
        Map<String,Object> map = new HashMap<>();
        String idsStr[] = smallIds.split(",");
        for(int i =0 ;i<idsStr.length;i++){
            DrawingType drawingType = new DrawingType();
            drawingType.setBigDrawing(bigDrawingService.findBigDrawingId(bigDrawingId));
            drawingType.setDrawing(drawingService.findById(Integer.parseInt(idsStr[i])));
            drawingTypeService.addSonDrawing(drawingType);
        }
        map.put("success",true);
        return map;

    }
}
