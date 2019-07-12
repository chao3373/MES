package com.shenke.controller.admin;


import com.shenke.entity.DrawingProcess;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingProcessService;
import com.shenke.service.DrawingService;
import com.shenke.service.ProcessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 查找状态为空的信息
     * @return
     */
    @RequestMapping("/findStateNull")
    public Map<String,Object> findStateNull(){
        Map<String,Object> map = new HashMap<>();
        List<DrawingProcess> list = drawingProcessService.findStateNull();
        map.put("rows",list);
        return map;
    }

    /**
     * 生产完成
     * @return
     */
    @RequestMapping("/setState")
    public Map<String,Object> setAuditState(Integer id){
        Map<String,Object> map = new HashMap<>();
        drawingProcessService.setState(id);
        map.put("success",true);
        return map;

    }

    /**
     * 查找状态为审核通过的信息
     * @return
     */
    @RequestMapping("/findAuditPass")
    public Map<String,Object> findAuditPass(){
        Map<String,Object> map = new HashMap<>();
        List<DrawingProcess> list = drawingProcessService.findAuditPass();
        map.put("rows",list);
        return map;
    }

    /**
     * 保存工序
     * @param DrawingId
     * @param ProcessIds
     * @param BigDrawingId
     * @param saleNumber
     * @param num
     * @return
     */
    @RequestMapping("/saveProcess")
    public Map<String,Object> saveProcess(Integer DrawingId,String ProcessIds,Integer BigDrawingId,String saleNumber,Integer num){
        Map<String,Object> map = new HashMap<>();
        String idsStr[] = ProcessIds.split(",");
        for(int i=0;i<idsStr.length;i++) {
            DrawingProcess drawingProcess = new DrawingProcess();
            drawingProcess.setBigDrawing(bigDrawingService.findById(BigDrawingId));
            drawingProcess.setDrawing(drawingService.findById(DrawingId)); //根据ID查询Drawing对象
            drawingProcess.setProcess(processService.findById(Integer.parseInt(idsStr[i])));
            drawingProcess.setState("任务下发");
            drawingProcess.setSaleNumber(saleNumber);
            drawingProcess.setNum(num);
            drawingProcess.setAccomplishNum(0);

            drawingProcessService.saveDrawingProcess(drawingProcess);
        }
        map.put("success",true);
        return map;
    }


    /**
     * 查找状态为“任务下发”的信息
     * @return
     */
    @RequestMapping("/findProcessIssue")
    public Map<String,Object> findProcessIssue(){
        Map<String,Object> map = new HashMap<>();
        List<DrawingProcess> list = drawingProcessService.findProcessIssue();
        map.put("rows",list);
        return map;
    }

    /**
     * 按照工序查找
     * @param id
     * @return
     */
    @RequestMapping("/findByProcess")
    public Map<String,Object> findByProcess(Integer id){
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        List<DrawingProcess> list = drawingProcessService.findByProcess(id);
        map.put("rows",list);
        return map;
    }

    /**
     * 更新按成数量
     * @param accomplishNum
     * @return
     */
    @RequestMapping("/updateAccomplishNum")
    public Map<String,Object> updateAccomplishNum(Integer accomplishNum,Integer id){
        Map<String,Object> map = new HashMap<>();
        drawingProcessService.updateAccomplishNum(accomplishNum,id);
        map.put("success",true);
        return map;
    }
}
