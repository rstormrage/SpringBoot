package com.blog.service;

import com.blog.vo.params.Result;
import com.blog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional

public interface LoginService {

    Result login(LoginParam loginParam);


    Result logout(String token);

    Result register(LoginParam loginParam);
}

