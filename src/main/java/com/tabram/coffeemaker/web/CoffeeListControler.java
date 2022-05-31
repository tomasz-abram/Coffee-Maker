package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoffeeListControler {


    private CoffeeAdminRepository coffeeAdminRepository;

    public CoffeeListControler(CoffeeAdminRepository coffeeAdminRepository) {
        this.coffeeAdminRepository = coffeeAdminRepository;
    }

    @GetMapping("/coffee-list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("coffee-list");
        mav.addObject("coffees", coffeeAdminRepository.findAll());
        return mav;
    }

    @PostMapping("/add-coffee") // -dodać DTO
    public String saveCoffee(@ModelAttribute CoffeeAdmin coffeeAdmin) {
        coffeeAdminRepository.save(coffeeAdmin);
        return "redirect:/coffee-list";
    }

    @GetMapping("/show-update-coffees")
    public ModelAndView showUpdateCoffees(@RequestParam Long coffeeId) {
        ModelAndView mav = new ModelAndView("add-coffee-form");
        CoffeeAdmin coffeeAdmin = coffeeAdminRepository.findById(coffeeId).get();
        mav.addObject("coffee", coffeeAdmin);
        return mav;
    }

    @GetMapping("/delete-coffee")
    public String deleteCoffee(@RequestParam Long coffeeId) {
        coffeeAdminRepository.deleteById(coffeeId);
        return "redirect:/coffee-list";
    }
}
