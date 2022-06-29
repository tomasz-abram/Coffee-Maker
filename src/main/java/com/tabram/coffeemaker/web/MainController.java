package com.tabram.coffeemaker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping( {"/", "home"})
    public String home() {
        return "home";
    }

    @GetMapping({"menu"})
    public String menu() {
        return "menu";
    }

}
