package com.vaibhav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @RequestMapping("/contact")
    public String saveContactEnquiryDetails(){
        return "My Contact Details";
    }
}
