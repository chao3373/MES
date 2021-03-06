package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.BigDrawing;
import com.shenke.entity.Drawing;
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

    @Resource
    private WuliaoService wuliaoService;


    @RequestMapping("/addSonDrawing")
    public Map<String,Object> addSonDrawing(String data,String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        //drawingTypeService.deleteByBigDrawingId(bigDrawing.getId());
        Gson gson = new Gson();
        List<Drawing> plgList = gson.fromJson(data, new TypeToken<List<Drawing>>() {
        }.getType());
        for (Drawing drawing : plgList){
            if (drawing.getId() == null) {
                DrawingType drawingType = new DrawingType();
                drawingType.setBigDrawing(bigDrawing);
                drawingType.setNum(drawing.getNum());
                drawingType.setDrawing(drawingService.findByWuliaoId(drawing.getWuliaoId()));
                drawingTypeService.addSonDrawing(drawingType);
            }
        }
        map.put("success",true);
        return map;
    }
    /*public Map<String,Object> addSonDrawing(String wuliaoId,String []smallIds,Integer id) throws Exception {
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        //判断是否已添加物料信息
        if(wuliaoService.findByBigDrawingId(bigDrawing.getId()) == null){
            map.put("success",false);
            map.put("errorInfo","请先添加生产物料！");
            return map;
        }

        for (int i = 0 ;i < smallIds.length; i++){
            DrawingType drawingType = new DrawingType();
            drawingType.setDrawing(drawingService.findByWuliaoId(smallIds[i]));
            drawingType.setBigDrawing(bigDrawingService.findByWuLiaoId(wuliaoId));
            drawingTypeService.addSonDrawing(drawingType);
        }
        saleListService.setCunZai(id,"存在");
        map.put("success",true);
        return map;
    }*/

    /**
     * 通过大图纸id查找
     * @param wuliaoId
     * @return
     */
    @RequestMapping("/findByBigDrawingId")
    public Map<String,Object> findByBigDrawingId(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        map.put("size",drawingTypeService.findByBigDrawingId(bigDrawing.getId()));
        return map;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Map<String,Object> deleteById(Integer id){
        Map<String,Object> map = new HashMap<>();
        drawingTypeService.deleteById(id);
        map.put("success",true);
        return map;
    }

    @RequestMapping("/xiugai")
    public Map<String,Object> xiugai(String wuliaoId,String tuZhiName,Integer num,Integer id){
        System.out.println("********************************");
        System.out.println(wuliaoId);
        System.out.println(tuZhiName);
        System.out.println(num);
        System.out.println(id);
        System.out.println("********************************");
        Map<String,Object> map = new HashMap<>();
        DrawingType drawingType = drawingTypeService.findById(id);
        drawingType.getDrawing().setWuliaoId(wuliaoId);
        drawingType.getDrawing().setTuZhiName(tuZhiName);
        drawingType.setNum(num);
        System.out.println("**************");
        System.out.println(drawingType);
        System.out.println("**************");
        drawingTypeService.save(drawingType);
        map.put("success",true);
        return map;
    }

}
