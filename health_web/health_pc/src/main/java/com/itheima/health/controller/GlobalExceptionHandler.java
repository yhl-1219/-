package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangweili
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Integer SUCCESS_CODE = 200;

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
        if (errorMsg.length() > SUCCESS_CODE) {
            return new Result(false, errorMsg.substring(0, 200) + "...");
        } else {
            return new Result(false, errorMsg);
        }
    }

}
