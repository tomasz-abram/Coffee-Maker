package com.tabram.coffeemaker;

import com.tabram.coffeemaker.coffee.Coffee;
import com.tabram.coffeemaker.coffee.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CoffeeListController {

    @Autowired
   private CoffeeRepository coffeeRepository;


    @GetMapping("/coffee-list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("coffee-list");
        mav.addObject("coffees", coffeeRepository.findAll());
        return mav;
    }

    @GetMapping("/add-coffee-form")
    public ModelAndView addCoffeeForm() {
        ModelAndView mav = new ModelAndView("add-coffee-form");
        Coffee newCoffee = new Coffee();
        mav.addObject("coffee", newCoffee);
        return mav;
    }


    @PostMapping("/add-coffee")
    public String saveCoffee(@ModelAttribute Coffee coffee) {
        coffeeRepository.save(coffee);
        return "redirect:/coffee-list";
    }

    @GetMapping("/show-update-coffees")
    public ModelAndView showUpdateForm(@RequestParam Integer coffeeId) {
        ModelAndView mav = new ModelAndView("add-coffee-form");
        Coffee coffee = coffeeRepository.findById(coffeeId).get();
        mav.addObject("coffee", coffee);
        return mav;
    }

    @GetMapping("/delete-coffee")
    public String deleteCoffee(@RequestParam Integer coffeeId) {
        coffeeRepository.deleteById(coffeeId);
        return "redirect:/coffee-list";
    }
}

