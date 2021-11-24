package com.backend.gimhanul.global.interceptor;

import com.backend.gimhanul.global.annotation.UserLoginToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    // Token 관련 DI


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request url: {}", request.getRequestURI());

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (!handlerMethod.getMethod().isAnnotationPresent(UserLoginToken.class)) {
            return true;
        }

        // Todo 토큰 가져오기

        // Todo 토큰 검사 해주기


        // Todo 유저 가져와서 request.setAttribute() 하기

        return true;
    }
}
