package com.shenke.controller.admin;


import com.shenke.entity.Clerk;
import com.shenke.entity.ClerkProduct;
import com.shenke.entity.Log;
import com.shenke.entity.Process;
import com.shenke.service.ClerkProductService;
import com.shenke.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 员工生产信息Control
 */
@RestController
@RequestMapping("/admin/clerkProduct")
public class ClerkProductAdminController {

    @Resource
    private ClerkProductService clerkProductService;

    @Resource
    private LogService logService;

    /**
     * 查找全部
     * @return
     */
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        Map<String,Object> map = new HashMap<>();
        List<ClerkProduct> list = clerkProductService.findAll();
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION, "查找所有员工工作信息"));
        return map;
    }

    /**
     * 查询生产状况
     * @return
     */
    @RequestMapping("/findShengchan")
    //Clerk clerk, Process process,String informNum
    public Map<String,Object> findShengchan(ClerkProduct clerkProduct,String dateInProducedd){
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("rows",clerkProductService.findShengchan(clerkProduct,dateInProducedd));
        logService.save(new Log(Log.SEARCH_ACTION, "查询生产信息"));
        return map;
    }
}
