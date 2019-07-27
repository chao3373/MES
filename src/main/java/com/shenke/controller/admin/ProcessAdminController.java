package com.shenke.controller.admin;

import com.shenke.entity.DrawingProcess;
import com.shenke.entity.Log;
import com.shenke.entity.Process;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingService;
import com.shenke.service.LogService;
import com.shenke.service.ProcessService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工时
 */
@RestController
@RequestMapping("/admin/process")
public class ProcessAdminController {

    @Resource
    private ProcessService processService;

    @Resource
    private DrawingService drawingService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private LogService logService;

    /**
     *  查询全部工时信息
     * @param process
     * @return
     */
    @RequestMapping("/list")
    public Map<String,Object> list(Process process){
        Map<String,Object> map = new HashMap();
        List<Process> list = processService.list(process);
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION,"查询工时信息"));
        return map;
    }

    /***
     * 模糊查询工时
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/processCombobox")
    public List<Process> processCombobox(String q) throws Exception {
        if (q == null) {
            q = "";
        }
        logService.save(new Log(Log.SEARCH_ACTION,"模糊查询工时"));
        return processService.findProcessCombobox("%" + q + "%");
    }
}
