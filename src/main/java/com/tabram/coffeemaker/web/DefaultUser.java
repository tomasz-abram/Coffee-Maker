package com.tabram.coffeemaker.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultUser {

    @GetMapping("/default")
    public void logDefaultUser(){
String userName = "default";
String password = "default";

    }



}
