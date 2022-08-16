package com.blog.service;

import com.blog.dao.pojo.User;
import com.blog.vo.UserVo;
import com.blog.vo.params.Result;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserService  {

    Result findUserByToken(String token);

    User findUserById(Long id) ;

    User findUser(String account, String password);


    User findUserByAccount(String account);

    void save(User user);


    UserVo findUserVoById(Long id);
}
