package com.itheima.Controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public R doException(Exception ex){


        ex.printStackTrace();
        return new R("服务器故障，重试！");
    }

}
