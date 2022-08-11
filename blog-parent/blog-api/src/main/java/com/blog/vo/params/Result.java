package com.blog.vo.params;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class Result {
    private boolean success;

    private int code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(true,200,"success",data);
    }

    public static Result fail(int code, String msg) {
        return new Result(false,code,msg,null);
    }
}


