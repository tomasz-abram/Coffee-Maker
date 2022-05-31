package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.CoffeeAdminDto;
import com.tabram.coffeemaker.service.CoffeeAdminServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/add-coffee-form")
@Controller
public class CoffeeAdminController {


    private CoffeeAdminServiceInterface coffeeAdminServiceInterface;

    public CoffeeAdminController(CoffeeAdminServiceInterface coffeeAdminServiceInterface) {
        this.coffeeAdminServiceInterface = coffeeAdminServiceInterface;
    }


    @ModelAttribute("coffeeAdmin")
    public CoffeeAdminDto coffeeAdminDto() {
        return new CoffeeAdminDto();
    }

    @GetMapping
    public String showAddCoffeeForm() {
        return "add-coffee-form";
    }


    @PostMapping
    public String addCoffeeAdmin(@ModelAttribute("coffeeAdmin") CoffeeAdminDto coffeeAdminDto) {
        coffeeAdminServiceInterface.saveCoffee(coffeeAdminDto);
        return "redirect:/coffee-list";
    }


//
//    @GetMapping("/add-coffee-form")
//    public ModelAndView addCoffeeForm() {
//        ModelAndView mav = new ModelAndView("add-coffee-form");
//        CoffeeAdmin newCoffeeAdmin = new CoffeeAdmin(coffeeAdminRepository.findAll());
//        mav.addObject("coffee", newCoffeeAdmin);
//        return mav;
//    }
//

}

