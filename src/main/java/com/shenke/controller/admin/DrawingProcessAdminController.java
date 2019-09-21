package com.shenke.controller.admin;


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
    private TemporaryStorageService temporaryStorageService;

    @Resource
    private SaleListService saleListService;

    @Resource
    private DrawingTypeService drawingTypeService;

    @Resource
    private LogService logService;

    @Resource
    private ClerkProductService clerkProductService;

    @Resource
    private ClerkService clerkService;

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(Integer []processIds,Integer drawingId){
        Map<String,Object> map = new HashMap<>();
        drawingProcessService.deleteByDrawingId(drawingId);
        if (processIds!=null) {
            for (int i = 0; i < processIds.length; i++) {
                DrawingProcess drawingProcess = new DrawingProcess();
                drawingProcess.setProcess(processService.findById(processIds[i]));
                drawingProcess.setDrawing(drawingService.findById(drawingId));
                drawingProcessService.save(drawingProcess);
            }
        }
        map.put("success",true);
        return map;
    }
}
