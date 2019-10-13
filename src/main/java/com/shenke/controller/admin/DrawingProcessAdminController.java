package com.shenke.controller.admin;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.*;
import com.shenke.service.*;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 图纸工序关系Controller
 *
 */
@RestController
@RequestMapping("/admin/drawingProcess")
public class DrawingProcessAdminController {

    @Resource
    private DrawingProcessService drawingProcessService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingService drawingService;

    @Resource
    private ProcessService processService;


    @Resource
    private SaleListService saleListService;

    @Resource
    private DrawingTypeService drawingTypeService;

    @Resource
    private LogService logService;


    @Resource
    private ClerkService clerkService;

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(String data,Integer drawingId){
        Map<String,Object> map = new HashMap<>();

        Gson gson = new Gson();
        List<DrawingProcess> plgList = gson.fromJson(data, new TypeToken<List<DrawingProcess>>() {
        }.getType());

        int i = 1;
        for (DrawingProcess drawingProcess : plgList){
            drawingProcess.setProcess(processService.findById(drawingProcess.getId()));
            drawingProcess.setId(null);
            drawingProcess.setCzGongShi(drawingProcess.getCzGongShi());
            drawingProcess.setZbGongShi(drawingProcess.getZbGongShi());
            drawingProcess.setDrawing(drawingService.findById(drawingId));
            drawingProcess.setCode(i);
            i+=1;
            drawingProcessService.save(drawingProcess);
        }
        map.put("success",true);
        return map;
    }
    /*@RequestMapping("/addProcess")
    public Map<String,Object> addProcess(Integer []processIds,Integer drawingId){
        Map<String,Object> map = new HashMap<>();
        drawingProcessService.deleteByDrawingId(drawingId);
        if (processIds!=null) {
            for (int i = 0; i < processIds.length; i++) {
                DrawingProcess drawingProcess = new DrawingProcess();
                drawingProcess.setCode(i+1);
                drawingProcess.setProcess(processService.findById(processIds[i]));
                drawingProcess.setDrawing(drawingService.findById(drawingId));
                drawingProcessService.save(drawingProcess);
            }
        }
        map.put("success",true);
        return map;
    }*/
}
