package com.shenke.controller.admin;


import com.shenke.entity.Clerk;
import com.shenke.entity.ClerkProduct;
import com.shenke.entity.Process;
import com.shenke.service.ClerkProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/clerkProduct")
public class ClerkProductAdminController {

    @Resource
    private ClerkProductService clerkProductService;

    /**
     * 查找全部
     * @return
     */
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        Map<String,Object> map = new HashMap<>();
        List<ClerkProduct> list = clerkProductService.findAll();
        map.put("rows",list);
        return map;
    }

    /**
     * 查询生产状况
     * @return
     */
    @RequestMapping("/findShengchan")
    //Clerk clerk, Process process,String informNum
    public Map<String,Object> findShengchan(ClerkProduct clerkProduct,String dateInProducedd){
        System.out.println("*****************************");
        System.out.println(dateInProducedd);
        System.out.println("*****************************");
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("rows",clerkProductService.findShengchan(clerkProduct,dateInProducedd));
        return map;
    }
}
