package com.shenke.controller;

import com.shenke.service.RoleMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
}
