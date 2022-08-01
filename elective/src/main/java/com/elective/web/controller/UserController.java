package com.elective.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.elective.constant.SystemConstant.USER_KEY;

@Controller
public class UserController {

    @Resource
    private HttpSession httpSession;

    @GetMapping("/logout")
    public String logout(){
        httpSession.removeAttribute(USER_KEY);
        return "redirect:/login";
    }
}
