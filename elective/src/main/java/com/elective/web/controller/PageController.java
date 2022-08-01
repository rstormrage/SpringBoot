package com.elective.web.controller;

import com.elective.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Resource
    private UserService userService;

    @Resource
    private HttpSession httpSession;

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        if(userService.getCurrentUser(httpSession) != null){
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(){

        return "dashboard";
    }
}
