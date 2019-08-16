package com.shenke.controller.admin;


import com.shenke.entity.DrawingType;
import com.shenke.entity.Log;
import com.shenke.service.*;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大小图纸关系Controller
 */
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

    @Resource
    private LogService logService;


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
        logService.save(new Log(Log.ADD_ACTION,"添加图图纸信息"));
        return map;
    }

    /**
     * 根据id修改状态
     * @param id
     * @param state
     */
    @RequestMapping("/setState")
    public void setState(Integer id,String state){
        logService.save(new Log(Log.UPDATE_ACTION,"根据id修改状态"));
        drawingTypeService.setState(id,state);
    }

    /**
     * 根据saleListId查询
     * @param id
     * @return
     */
    @RequestMapping("/findBySaleListId")
    public Map<String,Object> findBySaleListId(Integer id){
        Map<String,Object> map = new HashMap<>();
        List<DrawingType> list = drawingTypeService.findBySaleListId(id);
        map.put("rows",list);
        return map;
    }
}
