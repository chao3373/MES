package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.BigDrawing;
import com.shenke.entity.BigDrawingProcess;
import com.shenke.entity.Log;
import com.shenke.entity.Process;
import com.shenke.service.BigDrawingProcessService;
import com.shenke.service.BigDrawingService;
import com.shenke.service.LogService;
import com.shenke.service.ProcessService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/bigDrawingProcess")
public class BigDrawingProcessAdminController {

    @Resource
    private BigDrawingProcessService bigDrawingProcessService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private ProcessService processService;

    @Resource
    private LogService logService;

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(String data, String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        bigDrawingProcessService.deleteByDrawingId(bigDrawing.getId());

        Gson gson = new Gson();
        List<BigDrawingProcess> plgList = gson.fromJson(data, new TypeToken<List<BigDrawingProcess>>() {
        }.getType());

        int i = 1;
        String gongxus = "";
        for (BigDrawingProcess bigDrawingProcess : plgList){

            System.out.println("********************************");
            System.out.println(bigDrawingProcess);
            System.out.println("********************************");
            Process process = processService.findById(bigDrawingProcess.getCode());
            gongxus += process.getName()+",";
            bigDrawingProcess.setProcess(process);
            bigDrawingProcess.setId(null);
            bigDrawingProcess.setZbGongShi(bigDrawingProcess.getZbGongShi());
            bigDrawingProcess.setBigDrawing(bigDrawing);
            bigDrawingProcess.setCode(i);
            i+=1;
            bigDrawingProcessService.save(bigDrawingProcess);
        }
        map.put("success",true);
        logService.save(new Log(Log.UPDATE_ACTION,"(总图)物料号："+wuliaoId+";添加工序："+gongxus));
        return map;
    }

    /**
     * 通过大图纸对象id查找
     * @param wuliaoId
     * @return
     */
    @RequestMapping("/findByBigDrawingId")
    public Map<String,Object> findByBigDrawingId(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        List<BigDrawingProcess> list = bigDrawingProcessService.findByBigDrawingId(bigDrawingService.findByWuLiaoId(wuliaoId).getId());
        map.put("rows",list);
        map.put("success",true);
        return map;
    }
}
