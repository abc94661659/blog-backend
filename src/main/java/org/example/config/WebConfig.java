package org.example.config;

import org.example.utils.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")// 拦截所有请求
                .excludePathPatterns("/api/users/admin","/api/statistics/updateAndGet","/api/users/check")
                .excludePathPatterns("/api/users/register","/api/users/login","/api/users/forget-password");
    }
}

