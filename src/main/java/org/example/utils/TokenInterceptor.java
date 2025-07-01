package org.example.utils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UserContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 获取请求路径
        String requestURI = request.getRequestURI();

        if (requestURI.contains("/login") || requestURI.contains("/register")|| requestURI.contains("/forget-password")
                ||requestURI.contains("/check") || requestURI.contains("/announcements") || requestURI.contains("/forgot-password")
        || requestURI.contains("/verify-code")) {
            log.info("不用令牌路径，放行");
            return true;
        }
        // 获取请求头中的 Authorization 字段
        String authorizationHeader = request.getHeader("Authorization");
        // 判断 Authorization 字段是否存在
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.info("令牌为空或格式错误，不予放行");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 提取 Token
        String token = authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
        // 如果 token 存在，校验令牌，如果校验失败->返回错误信息 401
        try {
            Claims claims = JwtUtils.parseJWT(token);
            // 获取 claims 中的 id 字段
            Integer id = Integer.parseInt(claims.get("id").toString());
            // 设置到 ThreadLocal
            UserContextHolder.setUserId(id);
        } catch (Exception e) {
            log.info("令牌非法,响应 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 放行
        log.info("令牌合法,放行");
        return true;
    }
}