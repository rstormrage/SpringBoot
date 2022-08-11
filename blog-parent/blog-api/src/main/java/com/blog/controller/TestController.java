package com.blog.controller;

import com.blog.dao.pojo.User;
import com.blog.utils.UserThreadLocal;
import com.blog.vo.params.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
//        SysUser
        User user = UserThreadLocal.get();
        System.out.println(user);
        return Result.success(null);
    }
}
