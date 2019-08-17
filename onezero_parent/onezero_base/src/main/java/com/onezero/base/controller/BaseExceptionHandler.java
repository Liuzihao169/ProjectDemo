package com.onezero.base.controller;

import entity.Result;
import entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hao
 * @create 2019-07-14 ${TIM}
 * 全局异常处理
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
    @ExceptionHandler(value = {Exception.class})
    public Result excception(Exception ex){
        // 打印日志
        ex.printStackTrace();
       return  new Result(false, StatusCode.ERROR,ex.getMessage());
    }
}
