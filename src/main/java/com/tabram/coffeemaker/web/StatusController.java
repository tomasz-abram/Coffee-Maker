package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.config.CoffeeMachine;
import com.tabram.coffeemaker.model.CoffeeMachineStatus;
import com.tabram.coffeemaker.repository.CoffeeMachineStatusRepository;
import com.tabram.coffeemaker.service.CoffeeMachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/status-coffee-machine")
public class StatusController {

    private final CoffeeMachineStatusRepository coffeeMachineStatusRepository;
    private final CoffeeMachineStatusService coffeeMachineStatusService;

    @Autowired
    public StatusController(CoffeeMachineStatusRepository coffeeMachineStatusRepository, CoffeeMachineStatusService coffeeMachineStatusService) {
        this.coffeeMachineStatusRepository = coffeeMachineStatusRepository;
        this.coffeeMachineStatusService = coffeeMachineStatusService;
    }

    @GetMapping
    public ModelAndView getStatus() {
        ModelAndView mav = new ModelAndView("/status-coffee-machine");
        mav.addObject("status", coffeeMachineStatusRepository.findById(1L));
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        mav.addObject("machine", coffeeMachine);
        CoffeeMachineStatus coffeeMachineStatus = new CoffeeMachineStatus();
        mav.addObject("coffeeMachineStatus", coffeeMachineStatus);
        return mav;
    }

    @PostMapping("/user")
    public String setWaterHardness(@ModelAttribute("machineStatus") CoffeeMachineStatus coffeeMachineStatus) {
        coffeeMachineStatusService.updateWaterHardness(coffeeMachineStatus);
        return "redirect:/status-coffee-machine";
    }


    @GetMapping("/fill-water-tank")
    public String fillWater(@RequestParam int quantity) {
        coffeeMachineStatusService.updateWater(quantity);
        return "redirect:/status-coffee-machine";
    }

    @GetMapping("/empty-water-tank")
    public String emptyWater() {
        coffeeMachineStatusService.emptyWater();
        return "redirect:/status-coffee-machine";
    }


    @GetMapping("/fill-milk-tank")
    public String fillMilk(@RequestParam int quantity) {
        coffeeMachineStatusService.updateMilk(quantity);
        return "redirect:/status-coffee-machine";
    }

    @GetMapping("/empty-milk-tank")
    public String emptyMilk() {
        coffeeMachineStatusService.emptyMilk();
        return "redirect:/status-coffee-machine";
    }

    @GetMapping("/fill-beans-container")
    public String fillBeans(@RequestParam int quantity) {
        coffeeMachineStatusService.updateBeans(quantity);
        return "redirect:/status-coffee-machine";
    }

    @GetMapping("/empty-coffee-beans")
    public String emptyCoffeeBeans() {
        coffeeMachineStatusService.emptyCoffeeBeans();
        return "redirect:/status-coffee-machine";
    }

    @GetMapping("/empty-ground-container")
    public String cleanGroundContainer() {
        coffeeMachineStatusService.emptyGroundContainer();
        return "redirect:/status-coffee-machine";
    }


    @GetMapping("/descale")
    public String descale() {
        coffeeMachineStatusService.descale();
        return "redirect:/status-coffee-machine";
    }
}
