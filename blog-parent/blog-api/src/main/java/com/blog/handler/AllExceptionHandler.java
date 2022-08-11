package com.blog.handler;

import com.blog.vo.params.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {


    @ResponseBody //返回json数据
    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception){
        exception.printStackTrace();
        return Result.fail(-999, "System except from GlobalExceptionHandler");
    }
}
