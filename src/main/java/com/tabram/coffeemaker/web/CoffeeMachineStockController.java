package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.config.CoffeeMachine;
import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import com.tabram.coffeemaker.service.CoffeeMachineStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stock-coffee-machine")
public class CoffeeMachineStockController {

    private final CoffeeMachineStockRepository coffeeMachineStockRepository;
    private final CoffeeMachineStockService coffeeMachineStockService;

    @Autowired
    public CoffeeMachineStockController(CoffeeMachineStockRepository coffeeMachineStockRepository, CoffeeMachineStockService coffeeMachineStockService) {
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
        this.coffeeMachineStockService = coffeeMachineStockService;
    }

    @ModelAttribute("coffeeMachineStock")
    public CoffeeMachineStock coffeeMachineStock() {
        return new CoffeeMachineStock();
    }

    @GetMapping
    public ModelAndView getStock() {
        ModelAndView mav = new ModelAndView("stock-coffee-machine");
        mav.addObject("waterStock", coffeeMachineStockRepository.findByName("Water"));
        mav.addObject("milkStock", coffeeMachineStockRepository.findByName("Milk"));
        mav.addObject("coffeeBeansStock", coffeeMachineStockRepository.findByName("Coffee beans"));
        mav.addObject("groundContainerStock", coffeeMachineStockRepository.findByName("Ground container"));
        mav.addObject("descaleCounter", coffeeMachineStockRepository.findByName("Descale counter"));
        mav.addObject("waterHardnessStock", coffeeMachineStockRepository.findByName("Water hardness"));
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        mav.addObject("machine", coffeeMachine);
        return mav;
    }

    @PostMapping("/user")
    public String setWaterHardness(@ModelAttribute("coffeeMachineStock") CoffeeMachineStock cMS) {
        coffeeMachineStockService.updateWaterHardness(cMS.getValue());
        return "redirect:/stock-coffee-machine";
    }

    @GetMapping("/fill-water-tank")
    public String fillWater(@RequestParam int quantity) {
        coffeeMachineStockService.updateWater(quantity);
        return "redirect:/stock-coffee-machine";
    }

    @GetMapping("/empty-water-tank")
    public String emptyWater() {
        coffeeMachineStockService.emptyWater();
        return "redirect:/stock-coffee-machine";
    }


    @GetMapping("/fill-milk-tank")
    public String fillMilk(@RequestParam int quantity) {
        coffeeMachineStockService.updateMilk(quantity);
        return "redirect:/stock-coffee-machine";
    }

    @GetMapping("/empty-milk-tank")
    public String emptyMilk() {
        coffeeMachineStockService.emptyMilk();
        return "redirect:/stock-coffee-machine";
    }

    @GetMapping("/fill-beans-container")
    public String fillBeans(@RequestParam int quantity) {
        coffeeMachineStockService.updateBeans(quantity);
        return "redirect:/stock-coffee-machine";
    }

    @GetMapping("/empty-coffee-beans")
    public String emptyCoffeeBeans() {
        coffeeMachineStockService.emptyCoffeeBeans();
        return "redirect:/stock-coffee-machine";
    }

    @GetMapping("/empty-ground-container")
    public String cleanGroundContainer() {
        coffeeMachineStockService.emptyGroundContainer();
        return "redirect:/stock-coffee-machine";
    }


    @GetMapping("/descale")
    public String descale() {
        coffeeMachineStockService.descale();
        return "redirect:/stock-coffee-machine";
    }
}