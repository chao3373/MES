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
    public Map<String,Object> addSonDrawing(Integer id,String smallIds, String bigDrawingId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        String idsStr[] = smallIds.split(",");

        StringBuffer openNum = new StringBuffer("ZK");
        String a = saleListService.getMaxOpenNum();
        if (a != null) {
            openNum.append(StringUtil.formatCode(a));
        } else {
            openNum.append("00001");
        }
        for(int i =0 ;i<idsStr.length;i++){
            DrawingType drawingType = new DrawingType();
            drawingType.setBigDrawing(bigDrawingService.findBigDrawingId(bigDrawingId));
            drawingType.setDrawing(drawingService.findById(Integer.parseInt(idsStr[i])));
            drawingTypeService.addSonDrawing(drawingType);
        }

        System.out.println("展开单号展开单号展开单号展开单号展开单号"+openNum);

        saleListService.setOpenNum(id,openNum.toString());
        saleListService.setState(id,"图纸展开");
        map.put("success",true);
        return map;

    }
}
