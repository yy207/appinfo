package com.interceptors;

import com.pojo.BackendUser;
import com.utils.Contains;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BackendPreInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        BackendUser backendUser = (BackendUser) session.getAttribute(Contains.CURRENT_USER);
        if (backendUser!=null){
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/index.jsp");
        return false;
    }
}
