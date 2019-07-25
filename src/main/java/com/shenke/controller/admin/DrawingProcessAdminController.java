package com.shenke.controller.admin;


import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import com.shenke.entity.TemporaryStorage;
import com.shenke.service.*;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.util.Arrays;
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

    @Resource
    private TemporaryStorageService temporaryStorageService;

    @Resource
    private SaleListService saleListService;

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
     * 保存工序
     * @param DrawingId
     * @param ProcessIds
     * @param BigDrawingId
     * @param saleNumber
     * @param num
     * @return
     */
    @RequestMapping("/saveProcess")
    public Map<String,Object> saveProcess(Integer DrawingId,String ProcessIds,String BigDrawingId,
                                          String saleNumber,Integer num,String informNum,Integer saleListId) {
        Map<String,Object> map = new HashMap<>();
        Integer id = bigDrawingService.findIdByDrawingId(BigDrawingId);
        String idsStr[] = ProcessIds.split(",");
        for(int i=0;i<idsStr.length;i++) {
            DrawingProcess drawingProcess = new DrawingProcess();
            drawingProcess.setBigDrawing(bigDrawingService.findById(id));
            drawingProcess.setDrawing(drawingService.findById(DrawingId)); //根据ID查询Drawing对象
            drawingProcess.setProcess(processService.findById(Integer.parseInt(idsStr[i])));
            drawingProcess.setState("任务下发");
            drawingProcess.setSaleNumber(saleNumber);
            drawingProcess.setNum(num);
            drawingProcess.setAccomplishNum(0);
            drawingProcess.setInformNum(informNum);
            drawingProcess.setSaleList(saleListService.findById(saleListId));

            System.out.println(drawingProcess);
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

    /**
     * 判断生产通知单是否完成
     * @param informNum
     * @return
     */
    @RequestMapping("/isNoFinish")
    public void isNoFinish(String informNum,Integer saleListId){

        List<DrawingProcess> list = drawingProcessService.findByInformNum(informNum);
        int m = 0;
        for(DrawingProcess drawingProcess : list){
            if(drawingProcess.getState().equals("生产完成")){
                m=m+1;
            }
        }
        if(m == list.size()){
            TemporaryStorage temporaryStorage = new TemporaryStorage();
            temporaryStorage.setBigDrawing(bigDrawingService.findById(list.get(0).getBigDrawing().getId()));
            temporaryStorage.setDrawing(drawingService.findById(list.get(0).getDrawing().getId()));

            temporaryStorageService.save(temporaryStorage);
        }

        List<DrawingProcess> list2 = drawingProcessService.findBySaleListId(saleListId);

        int n = 0;
        for(DrawingProcess drawingProcess : list2){
            if(drawingProcess.getState().equals("生产完成")){
                n = n + 1;
            }
        }
        if(n == list2.size()){
            saleListService.setState(saleListId,"生产完成");
        }
    }

    /**
     * 判断所选工序之前还有没有未完成工序
     * @param processId
     * @param informNum
     * @return
     */
    @RequestMapping("/whetherSamllerInformNum")
    public Map<String,Object> whetherSamllerInformNum(Integer processId,String informNum){
        Map<String,Object> map = new HashMap<>();
        Integer min = drawingProcessService.findMinProcess(informNum);
        if(processId==min){
            map.put("success",true);
        } else {
          map.put("success",false);
        }
        return map;
    }

    /**
     * 获取生产单号
     * @return
     * @throws Exception
     */
    @RequestMapping("/genCode")
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("SC");
        String informNum = drawingProcessService.getTodayMaxinformNumNumber();
        if (informNum != null) {
            code.append(StringUtil.formatCode(informNum));
        } else {
            code.append("0000001");
        }
        return code.toString();
    }

}
