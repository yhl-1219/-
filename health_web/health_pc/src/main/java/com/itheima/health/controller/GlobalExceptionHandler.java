package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangweili
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理
     *
     * @param ex 异常
     * @return 异常处理返回
     */
    @ExceptionHandler
    public Result exceptionHandle(Exception ex) {
        ex.printStackTrace();
        String errorMsg = ex.getMessage();
        if (errorMsg.length() > 200) {
            return new Result(false, errorMsg.substring(0, 200) + "...");
        } else {
            return new Result(false, errorMsg);
        }
    }

}
