package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统出现异常：", e);
        return Result.error("系统出现异常，请稍后再试：" + e.getMessage());
    }
}
