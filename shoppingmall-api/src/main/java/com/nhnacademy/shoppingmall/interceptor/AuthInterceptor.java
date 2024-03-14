package com.nhnacademy.shoppingmall.interceptor;

import com.nhnacademy.shoppingmall.exception.UnauthorizedException;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
                String userId = request.getHeader("X-USER-ID");
                if(!StringUtils.hasText(userId)){
                    throw new UnauthorizedException();
                }
                UserIdStore.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
        UserIdStore.remove();
    }
}
