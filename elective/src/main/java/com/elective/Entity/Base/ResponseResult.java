package com.elective.Entity.Base;

import java.io.Serializable;

public class ResponseResult<T>  {

    //private static final long serialVersionUID = 123456;
    // status code
    private Integer code;
    private String message;
    private T data;


    public Integer getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //Default constructor
    public ResponseResult(){
        super();
    }

    public ResponseResult(Integer code){
        super();
        this.code = code;
    }

    public ResponseResult(Integer code, String message){
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, Throwable throwable){
        super();
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ResponseResult(Integer code, T data){
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message, T data){
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
