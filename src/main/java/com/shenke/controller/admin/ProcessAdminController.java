package com.shenke.controller.admin;

import com.shenke.entity.DrawingProcess;
import com.shenke.entity.Log;
import com.shenke.entity.Process;
import com.shenke.entity.UserProcess;
import com.shenke.service.*;
import com.shenke.util.StringUtil;
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
    private ShengChanService shengChanService;

    @Resource
    private UserProductService userProductService;

    @Resource
    private UserProcessService userProcessService;

    @Resource
    private DrawingProcessService drawingProcessService;

    @Resource
    private BigDrawingProcessService bigDrawingProcessService;

    @Resource
    private LogService logService;

    @Resource
    private ProcessGroupService processGroupService;



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

    @RequestMapping("/save")
    public Map<String,Object> save(Integer id,String name){
        Map<String,Object> map = new HashMap<>();
        List<Process> list = processService.findByName(name);
        if(list.size() >= 1){
            map.put("success",true);
            map.put("error",name+"工序已存在！");
        }else {
            Process process = new Process();
            process.setName(name);
            process.setProcessGroup(processGroupService.findById(id));
            processService.save(process);
            map.put("success",true);
        }
        return map;
    }

    @RequestMapping("/deleteByIds")
    public void deleteByIds(Integer[] ids){
        userProductService.deleteByProcessIds(ids);
        shengChanService.deleteByProcessIds(ids);
        userProcessService.deleteByProcessIds(ids);
        drawingProcessService.deleteByProcessIds(ids);
        bigDrawingProcessService.deleteByProcessIds(ids);
        processService.deleteByIds(ids);
    }

    @RequestMapping("/findByPGId")
    public Map<String,Object> findByPGId(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",processService.findByPGId(id));
        map.put("success",true);
        return map;
    }

    /***
     * 根据姓名模糊查询员工信息
     * @param
     * @return
     */
    @RequestMapping("/selectByName")
    public Map<String, Object> selectByName(String name){
        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isEmpty(name)){
            name = "";
        }
        map.put("rows", processService.selectByName("%" + name + "%"));
        logService.save(new Log(Log.SEARCH_ACTION, "根据员工姓名模糊查询员工信息"));
        map.put("success", true);
        return map;
    }

    /**
     * 设置工序是否为多选
     * @param Ids
     * @param duoXuan
     * @return
     */
    @RequestMapping("/duoXuan")
    public Map<String,Object> setDuoXuan(Integer []Ids,String duoXuan){
        Map<String,Object> map = new HashMap<>();
        processService.setDuoXuan(Ids,duoXuan);
        map.put("success",true);
        return map ;
    }
}

