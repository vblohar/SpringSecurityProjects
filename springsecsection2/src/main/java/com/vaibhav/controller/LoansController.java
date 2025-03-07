package com.vaibhav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @RequestMapping("/myLoans")
    public String getLoansDetails(){
        return "My Loans Details";
    }
}
