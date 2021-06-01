package com.safecornerscoffee.interceptor;

import com.safecornerscoffee.service.dto.UserDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenicationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");

        if (user == null) {
            return false;
        }

        return true;
    }
}
