package com.hieunt.hackathon_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String getFormLogin() {
        return "login";
    }

    @GetMapping("/login-error")
    public String getLoginError() {
        return "/error/login_error";
    }
}
