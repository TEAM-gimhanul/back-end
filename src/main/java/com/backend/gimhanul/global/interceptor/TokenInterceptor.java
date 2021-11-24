package com.backend.gimhanul.global.interceptor;

import com.backend.gimhanul.global.annotation.UserLoginToken;
import com.backend.gimhanul.global.security.jwt.JwtTokenProvider;
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
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

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

        request.setAttribute("user", jwtTokenProvider.validate(
                jwtTokenProvider.extract(request)
        ));

        return true;
    }
}
