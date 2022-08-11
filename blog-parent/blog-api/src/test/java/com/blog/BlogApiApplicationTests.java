package com.blog;

import com.blog.dao.pojo.User;
import com.blog.service.ArticleService;
import com.blog.service.LoginService;
import com.blog.utils.UserThreadLocal;
import com.blog.vo.params.LoginParam;
import com.blog.vo.params.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
class BlogApiApplicationTests {

    @Resource
    private  ArticleService articleService;

    @Resource
    private LoginService loginService;
    @Test
    void contextLoads() {
    }



    @Test
    void createPSW(){
        System.out.println(DigestUtils.md5Hex("admin"));
    }

    @Test
    void login(){
        LoginParam loginParam = new LoginParam();
        loginParam.setAccount("admin");
        loginParam.setPassword(DigestUtils.md5Hex("admin"));

        loginService.login(loginParam);
    }

    @Test
    void testTimeStamp(){
        Timestamp timestamp;
        timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);
    }



}