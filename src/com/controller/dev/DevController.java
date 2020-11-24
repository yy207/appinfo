package com.controller.dev;

import com.pojo.DevUser;
import com.service.DevUserService;
import com.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dev")
public class DevController {
    @Autowired
    private DevUserService devUserService;
    //功能实现

    /**
     *
     * @return 登录
     */
    @RequestMapping("/dologin")
    public String dologin(HttpSession session, HttpServletRequest request){
        String devCode = request.getParameter("devCode");
        String devPassword = request.getParameter("devPassword");
        DevUser devUser = devUserService.login(devCode,devPassword);
        if (devUser != null){
            session.setAttribute(Contains.DEV_USER_SESSION,devUser);
            return "developer/main";
        }
        request.setAttribute(Contains.ERROR_MSG,"用户名或密码错误!");
        return "devlogin";
    }

    /**
     *
     * @return 注销
     */
    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/index.jsp";
    }

    /**
     *
     * @return
     */
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
