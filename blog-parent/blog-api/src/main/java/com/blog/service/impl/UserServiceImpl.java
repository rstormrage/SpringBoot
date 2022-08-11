package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.service.TokenService;
import com.blog.service.UserService;
import com.blog.dao.mapper.UserMapper;
import com.blog.dao.pojo.User;
import com.blog.vo.UserVo;
import com.blog.vo.params.ErrorCode;
import com.blog.vo.LoginUserVo;
import com.blog.vo.params.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1、token合法性校验
         *    是否为空，redis是否存在
         * 2、如果校验失败 返回错误
         * 3、如果成功 返回对应的结果 LoginUserVo
         */
        User sysUser = tokenService.checkToken(token);
        if (sysUser == null) {
            Result.fail(ErrorCode.TOKEN_NOT_EXIST.getCode(),ErrorCode.TOKEN_NOT_EXIST.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        return Result.success(loginUserVo);

    }

    @Override
    public User findUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null){
            user = new User();
            user.setNickname("Audrey Hall");
        }
        return user;
    }

    @Override
    public User findUser(String account, String password) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getAccount,account);
        lqw.eq(User::getPassword,password);
        lqw.select(User::getId,User::getAccount,User::getAvatar,User::getNickname);
        lqw.last("limit 1");
        User user = userMapper.selectOne(lqw);
        return user;
    }

    @Override
    public User findUserByAccount(String account) {
        LambdaQueryWrapper<User>  lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getAccount,account);
        lqw.last("limit 1");
        return this.userMapper.selectOne(lqw);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }
//
//    @Override
//    public UserVo findUserVoById(Long id) {
//        User sysUser = userMapper.selectById(id);
//        if (sysUser == null) {
//            sysUser = new User();
//            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
//            sysUser.setNickname("码神之路");
//        }
//        UserVo userVo = new UserVo();
//        BeanUtils.copyProperties(sysUser,userVo);
//        return userVo;
//    }
}
