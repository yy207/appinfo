package com.controller.dev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class DevController {

    //功能实现
    @RequestMapping("/dologin")
    public String dologin(){
        return "developer/main";
    }
    //功能实现
    @RequestMapping("/flatform/app/list")
    public String flatform(){
        return "developer/appinfolist";
    }

    //跳转页面

    /**
     *
     * @return 开发者登录页
     */
    @RequestMapping("/login")
    public String login(){
        return "devlogin";
    }





}
