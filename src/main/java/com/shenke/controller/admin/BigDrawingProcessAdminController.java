package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.BigDrawing;
import com.shenke.entity.BigDrawingProcess;
import com.shenke.service.BigDrawingProcessService;
import com.shenke.service.BigDrawingService;
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

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(String data, String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        bigDrawingProcessService.deleteByDrawingId(bigDrawing.getId());

        Gson gson = new Gson();
        List<BigDrawingProcess> plgList = gson.fromJson(data, new TypeToken<List<BigDrawingProcess>>() {
        }.getType());

        int i = 1;
        for (BigDrawingProcess bigDrawingProcess : plgList){

            System.out.println("********************************");
            System.out.println(bigDrawingProcess);
            System.out.println("********************************");
            bigDrawingProcess.setProcess(processService.findById(bigDrawingProcess.getCode()));
            bigDrawingProcess.setId(null);
            bigDrawingProcess.setZbGongShi(bigDrawingProcess.getZbGongShi());
            bigDrawingProcess.setBigDrawing(bigDrawing);
            bigDrawingProcess.setCode(i);
            i+=1;
            bigDrawingProcessService.save(bigDrawingProcess);
        }
        map.put("success",true);
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
