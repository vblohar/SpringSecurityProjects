package com.vaibhav.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @RequestMapping("/myCards")
    public String getCardsDetails(){
        return "My Cards Details";
    }
}
