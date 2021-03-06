package com.shenke.controller.admin;


import com.shenke.entity.UserProduct;
import com.shenke.service.UserProductService;
import com.shenke.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/admin/userProduct")
public class UserProductAdminController {

    @Resource
    private UserProductService userProductService;

    @RequestMapping("/list")
    public Map<String,Object> list(Integer process_id,Integer processGroup,String user_trueName,String wuliaoId,String saleNumber,String xiangmuId,String btime,String etime,Integer page,Integer rows) {
        return userProductService.list(process_id,processGroup,user_trueName,wuliaoId,saleNumber,xiangmuId,btime,etime,page,rows);
    }

    @RequestMapping("/findShengchan")
    public Map<String,Object> findShengchan(String dateInProduceStart,String process, String user,String dateInProduceEnd){
        Map<String,Object> map = new HashMap<>();
        System.out.println(dateInProduceStart);
        System.out.println(process);
        System.out.println(user);
        System.out.println(dateInProduceEnd);
        return map;
    }

    @RequestMapping("/aa/aa/aa")
    public void aaa(){

        List<UserProduct> list = userProductService.findAll();


        Date d1 = null;
        Date d2 = null;



        for(UserProduct userProduct : list){
            d1 = userProduct.getDateInProduct();
            break;
        }

        for(UserProduct userProduct : list){
            d2 = userProduct.getDateInProduct();
        }

        System.out.println(d1);
        System.out.println(d2);

        long between = d2.getTime() - d1.getTime();

        System.out.println(between/1000);


    }
}
