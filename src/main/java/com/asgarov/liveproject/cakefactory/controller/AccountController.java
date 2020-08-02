package com.asgarov.liveproject.cakefactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    @ResponseBody
    public String account(){
        return "Hello authorised user!";
    }

    @GetMapping("/test")
    @ResponseBody
    public String accountTest(){
        return "Hello authorised user in test!";
    }

}
