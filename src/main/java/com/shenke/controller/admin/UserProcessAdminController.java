package com.shenke.controller.admin;

import com.shenke.entity.UserProcess;
import com.shenke.service.ProcessService;
import com.shenke.service.UserProcessService;
import com.shenke.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/userProcess")
public class UserProcessAdminController {

    @Resource
    private UserProcessService userProcessService;

    @Resource
    private ProcessService processService;

    @Resource
    private UserService userService;

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(Integer []processIds,Integer yonghu){
        Map<String,Object> map = new HashMap<>();
        userProcessService.deleteByUserId(yonghu);
        if (processIds!=null) {
            for (int i = 0; i < processIds.length; i++) {
                UserProcess userProcess = new UserProcess();
                userProcess.setProcess(processService.findById(processIds[i]));
                userProcess.setUser(userService.findById(yonghu));
                userProcessService.save(userProcess);
            }
        }
        map.put("success",true);
        return map;
    }

}
