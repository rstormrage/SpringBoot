package com.elective.service;

import com.elective.Entity.Base.SystemRuntimeException;
import com.elective.Entity.User;
import com.elective.constant.SystemConstant;
import com.elective.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.elective.constant.SystemConstant.USER_KEY;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User login(String number, String password){
        User user = userRepository.findByNumber(number);
        if (user == null){
            throw new SystemRuntimeException("None user exist用户不存在Service");
//            return false;
        }
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            throw new SystemRuntimeException("Number or Password error账号密码错误Service");
        }
        return user;
    }

    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        return users;
    }

    public Boolean modifyPassword(User user, String password) {
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userRepository.save(user);
        return true;
    }

    public User getCurrentUser(HttpSession httpSession){
        Integer userId = (Integer)httpSession.getAttribute(USER_KEY );
        if (userId == null){
            throw new SystemRuntimeException("Please Login");
        }
        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            throw new SystemRuntimeException("User not exist");
        }
        return  user;
    }
}
