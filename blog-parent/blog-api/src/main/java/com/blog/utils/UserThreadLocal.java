package com.blog.utils;


import com.blog.dao.pojo.User;

/**
 * ThreadLocal保存用户信息
 */
public class UserThreadLocal {

    /**
     *  创建单例线程
     */
    private UserThreadLocal(){}
    // 线程变量隔离
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User sysUser){
        LOCAL.set(sysUser);
    }
    public static User get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
