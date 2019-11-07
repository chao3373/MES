package com.shenke.controller;

import com.shenke.service.RoleMenuService;
import com.shenke.util.DaochuUtil;
import com.shenke.util.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/5/ 2214:38
 * @Description:
 */
@Controller
public class IndexController {

    @Resource
    private RoleMenuService roleMenuService;

    @RequestMapping("/")
    public String index() {
        return "login.html";
    }

    @RequestMapping("/static/teess")
    public void test(){
        roleMenuService.teess();
    }

    // 根据数据库中的信息输出二维码
    @ResponseBody
    @RequestMapping("/static/erweima")
    public Map<String, Object> erweima(HttpServletRequest request, String url) throws Exception {
        System.out.println(url);
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(url);
        String urlInfo = QRCode.getQRCode(request, url);
//		String urlInfo = this.getURLInfo(url, "utf-8");
//		System.out.println("图片路径：");
//		System.out.println(urlInfo);
        map.put("success", true);
        map.put("url", urlInfo);
        System.out.println(map);
        return map;
    }

    /**
     * 导出
     * @param a
     * @param title
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/admin/daochu")
    public Map<String, Object> daochu(String a, String title) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println("((((((((((((((((导出)))))))))))))");
        System.out.println(a);
        System.out.println(a.getClass());
        System.out.println(title);
        System.out.println("((((((((((((((((导出)))))))))))))");
        DaochuUtil.daochuExcel(a, title);
        map.put("success", true);
        return map;
    }
}
