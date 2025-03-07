package com.vaibhav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping("/myAccount")
    public String getAccountDetails(){
        return "My Account Details";
    }
}
