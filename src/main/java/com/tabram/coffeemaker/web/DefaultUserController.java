package com.tabram.coffeemaker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefaultUserController {


    @PostMapping("/default")
    public String login() {


        return "redirect:/menu?logSuccess";
    }


}
