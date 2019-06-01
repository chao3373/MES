package com.shenke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Administrator
 * @Date: 2019/5/ 2214:38
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "login.html";
    }


}
