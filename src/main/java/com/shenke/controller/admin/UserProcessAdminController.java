package com.shenke.controller.admin;

import com.shenke.entity.Process;
import com.shenke.entity.User;
import com.shenke.entity.UserProcess;
import com.shenke.service.ProcessService;
import com.shenke.service.UserProcessService;
import com.shenke.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/processListByUser")
    public List<Process> processListByUser(HttpSession session,String q){
        User user = (User) session.getAttribute("currentUser"); //获取当前登录用户对象
        if(q == null){
            q = "";
        }
        List<UserProcess> list = userProcessService.processListByUser("%"+q+"%",user.getId());
        List<Process> list1 = new ArrayList<>();

        for (UserProcess userProcess : list){
            list1.add(userProcess.getProcess());
        }

        System.out.println("***************************");
        System.out.println(list1);
        System.out.println("***************************");
        return list1;
    }
}
