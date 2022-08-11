package com.blog.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.service.LoginService;
import com.blog.service.UserService;
import com.blog.dao.pojo.User;
import com.blog.utils.JWTUtils;
import com.blog.vo.params.ErrorCode;
import com.blog.vo.params.Result;
import com.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service

public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String salt = "mszlu!@###";

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1.检查参数是否合法
         * 2.根据用户名和密码去user表中查询是否存在
         * 3.如果不存在 登录失败
         * 4.如果存在，使用JWT生成token 返回给前段
         * 5.token放入redis当中，redis token：user 信息 设置过期时间
         * （登陆认证的时候 先认证token字符串是否合法，去redis认证是否存在）
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        // isBlank(判断字符串中是否全是空白字符)
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 在原有的密码加盐后进行md5加密
        password = DigestUtils.md5Hex(password);
        User userByAccount = userService.findUserByAccount(account);
        // 先查询账户是否存在 如不存在 则提示用户不存在
        if (userByAccount != null) {
            User sysUser = userService.findUser(account, password); // 不为空，此时判断密码是否正确
            if (sysUser == null) {
                return Result.fail(ErrorCode.ACCOUNT_PWD_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_ERROR.getMsg());
            } else {
                String token = JWTUtils.createToken(userByAccount.getId());
                redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(userByAccount), 1, TimeUnit.DAYS);
                return Result.success(token);
            }
        }
        // 用户账号不存在，则提示不存在
        return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank((account))
            ||StringUtils.isBlank(password)
            ||StringUtils.isBlank(nickname)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        User user = userService.findUserByAccount(account);
        if (user != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        user = new User();
        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setCreateDate(new Timestamp(new Date().getTime()));
        user.setLastLogin(new Timestamp(new Date().getTime()));
        user.setAvatar(null);
        user.setAdmin(1);
        user.setDeleted(false);
        user.setSalt("");
        user.setStatus("");
        user.setEmail("");
        this.userService.save(user);

        String token =JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}






