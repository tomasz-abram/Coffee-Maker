package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.service.CoffeeMachineStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    private final CoffeeMachineStockService coffeeMachineStockService;

    @Autowired
    public MainController(CoffeeMachineStockService coffeeMachineStockService) {
        this.coffeeMachineStockService = coffeeMachineStockService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping({"/menu"})
    public ModelAndView menu() {
        coffeeMachineStockService.checkStockStatus();
        ModelAndView mav = new ModelAndView("menu");
        mav.addObject("light", coffeeMachineStockService.alarmLightStockStatus());
        return mav;
    }
}
