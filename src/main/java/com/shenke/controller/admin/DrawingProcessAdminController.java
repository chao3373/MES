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

    @Resource
    private BigDrawingProcessService bigDrawingProcessService;

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(String data,Integer drawingId){
        Map<String,Object> map = new HashMap<>();
        drawingProcessService.deleteByDrawingId(drawingId);
        Gson gson = new Gson();
        List<DrawingProcess> plgList = gson.fromJson(data, new TypeToken<List<DrawingProcess>>() {
        }.getType());

        int i = 1;
        for (DrawingProcess drawingProcess : plgList){
            drawingProcess.setProcess(processService.findById(drawingProcess.getCode()));
            drawingProcess.setId(null);
            drawingProcess.setDrawing(drawingService.findById(drawingId));
            drawingProcess.setCode(i);
            i+=1;
            drawingProcessService.save(drawingProcess);
        }
        map.put("success",true);
        return map;
    }

    /**
     * 通过小图纸对象id查找
     * @param
     * @return
     */
    @RequestMapping("/findByDrawingId")
    public Map<String,Object> findByDrawingId(Integer id){
        Map<String,Object> map = new HashMap<>();
        List<DrawingProcess> list = drawingProcessService.findByDrawingId(drawingService.findById(id).getId());
        map.put("rows",list);
        map.put("success",true);
        return map;
    }

}
