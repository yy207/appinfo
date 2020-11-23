package com.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    //功能实现

    /**
     * 登录功能
     * @return
     */
    @RequestMapping("/dologin")
    public String dologin(){
        return "backend/main";
    }

    /**
     * app 列表
     * @return
     */
    @RequestMapping("/backend/app/list")
    public String applist(){
        return "backend/applist";
    }





    //页面 跳转

    /**
     *
     * @return 登录页
     */
    @RequestMapping("/login")
    public String login(){
        return "backendlogin";
    }

}
