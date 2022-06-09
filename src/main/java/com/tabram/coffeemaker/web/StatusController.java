package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.repository.CoffeeMachineStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController {

    CoffeeMachineStatusRepository coffeeMachineStatusRepository;

    @Autowired
    public StatusController(CoffeeMachineStatusRepository coffeeMachineStatusRepository) {
        this.coffeeMachineStatusRepository = coffeeMachineStatusRepository;
    }

    @GetMapping("/status-coffee-machine")
    public ModelAndView getStatus() {
        ModelAndView mav = new ModelAndView("status-coffee-machine");
        mav.addObject("status", coffeeMachineStatusRepository.findAll());
        return mav;
    }


}
