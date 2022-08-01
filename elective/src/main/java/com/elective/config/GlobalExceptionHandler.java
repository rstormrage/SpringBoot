package com.elective.config;

import com.elective.Entity.Base.SystemRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    public SystemRuntimeException resolveException(Exception exception) throws Exception{
        System.out.println(exception.getMessage());
        return new SystemRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());

    }
}
