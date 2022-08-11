package com.blog.service;

import com.blog.dao.pojo.User;

public interface TokenService {
    User checkToken(String token);
}
