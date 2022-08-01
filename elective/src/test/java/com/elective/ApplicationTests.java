package com.elective;

import com.elective.Entity.User;
import com.elective.repository.UserRepository;
import com.elective.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("admin3");
        user.setNumber("10003");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setRole((short)0);
        user.setStatus((short)1);
        userRepository.save(user);
    }

    @Autowired
    private UserService userService;
    @Test
    void login(){
        String number = "10001";
        String password = "admin";
        User login = userService.login(number, password);
        System.out.println(login);
    }

}
