package com.interceptors;

import com.pojo.BackendUser;
import com.pojo.DevUser;
import com.utils.Contains;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DevPreInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        DevUser devUser = (DevUser) session.getAttribute(Contains.CURRENT_USER);
        if (devUser!=null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");
        return false;
    }
}
