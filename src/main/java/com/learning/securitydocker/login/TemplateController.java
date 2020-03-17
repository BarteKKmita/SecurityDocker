package com.learning.securitydocker.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "logged in";
    }

    @GetMapping("/login-failure")
    public String loginFailure(){
        return "login failure";
    }
}
