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
    public Map<String,Object> list(Integer process_id,String user_trueName,String btime,String etime) throws ParseException {
        UserProduct userProduct = new UserProduct();

        if(process_id != null){
            userProduct.setProcessId(process_id);
        }

        if(StringUtil.isNotEmpty(user_trueName)){
            userProduct.setUserName(user_trueName);
        }

        if(StringUtil.isNotEmpty(etime) && StringUtil.isNotEmpty(btime)){

            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eDate = sdf.parse(etime);
            Date bDate = sdf.parse(btime);
*/
            java.util.Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(btime + " 00:00:00");
            java.util.Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(etime + " 23:59:59");

            userProduct.setbDate(start);
            userProduct.seteDate(end);

        }

        Map<String,Object> map = new HashMap<>();
        List<UserProduct> list = userProductService.list(userProduct);

        Double sumGongshi = 0.00;
        Double sumNum = 0.00;
        for(UserProduct userProduct1 : list){
            sumGongshi = sumGongshi + (userProduct1.getZbGongShi() + userProduct1.getCzGongShi()) * userProduct1.getNum();
            sumNum = sumNum + userProduct1.getNum();

        }
        map.put("sumGongshi",sumGongshi);
        map.put("sumNum",sumNum);
        map.put("rows",list);
        System.out.println(map);
        return map;
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
