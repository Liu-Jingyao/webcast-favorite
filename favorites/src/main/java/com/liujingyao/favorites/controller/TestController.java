package com.liujingyao.favorites.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @GetMapping("/")
    private String toIndex() {
        return "/index.html";
    }

    @GetMapping("/login")
    private String toLogin(){
        return "/login.html";
    }

    @GetMapping("/register")
    private String toRegister() {
        return "/register.html";
    }
}

