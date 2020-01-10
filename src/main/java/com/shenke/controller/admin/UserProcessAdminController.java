package com.shenke.controller.admin;

import com.shenke.entity.Log;
import com.shenke.entity.Process;
import com.shenke.entity.User;
import com.shenke.entity.UserProcess;
import com.shenke.service.LogService;
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

    @Resource
    private LogService logService;

    @RequestMapping("/addProcess")
    public Map<String,Object> addProcess(Integer []processIds,Integer yonghu){
        Map<String,Object> map = new HashMap<>();
        userProcessService.deleteByUserId(yonghu);

        String userName = "";
        String gongxu = "";
        if (processIds!=null) {
            User user = userService.findById(yonghu);
            for (int i = 0; i < processIds.length; i++) {
                UserProcess userProcess = new UserProcess();
                Process process = processService.findById(processIds[i]);
                userProcess.setProcess(process);
                userProcess.setUser(user);
                userProcessService.save(userProcess);
                gongxu = gongxu + process.getName() + ",";
            }
            userName = user.getTrueName();
        }
        logService.save(new Log(Log.UPDATE_ACTION,"给" + userName+"添加工序："+gongxu));
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
