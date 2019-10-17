package com.shenke.controller.admin;

import com.shenke.entity.Log;
import com.shenke.entity.Process;
import com.shenke.entity.ProcessGroup;
import com.shenke.service.LogService;
import com.shenke.service.ProcessGroupService;
import com.shenke.service.ProcessService;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/processGroup")
public class ProcessGroupAdminController {

    @Resource
    private ProcessGroupService processGroupService;

    @Resource
    private ProcessService processService;

    @Resource
    private LogService logService;

    /***
     * 查询所有部门信息
     * @return
     */
    @RequestMapping("/findAll")
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        List<ProcessGroup> list = processGroupService.findAll();
        map.put("data", list);
        map.put("success", true);
        logService.save(new Log(Log.SEARCH_ACTION, "查询所有信息"));
        return map;
    }

    /***
     * 添加部门
     * @param
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(String processGroup) {
        Map<String, Object> map = new HashMap<>();
        ProcessGroup processGroup1 = new ProcessGroup();
        processGroup1.setProcessGroup(processGroup);
        processGroupService.add(processGroup1);
        map.put("success", true);
        logService.save(new Log(Log.ADD_ACTION, "添加部门"));
        return map;
    }

    /***
     * 删除部门以及部门下的员工信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        List<Process> list = processService.findByPGId(id);
        if (list.size() > 0) {
            processService.deleteByPGId(id);
        }
        processGroupService.deleteById(id);
        map.put("success", true);
        logService.save(new Log(Log.DELETE_ACTION, "删除部门以及部门下的员工信息"));
        return map;
    }

    /***
     * 下拉框模糊查询所有部门信息
     * @param
     * @return
     */
    @RequestMapping("/down")
    public List<ProcessGroup> down(String s){
        if (StringUtil.isEmpty(s)){
            s = "";
        }
        logService.save(new Log(Log.SEARCH_ACTION, "模糊查询所有部门信息"));
        return processGroupService.findBytext("%" + s + "%");
    }

}
