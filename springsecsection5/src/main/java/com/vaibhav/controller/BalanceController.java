package com.vaibhav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @RequestMapping("/myBalance")
    public String getBalanceDetails(){
        return "My Balance Details";
    }
}
