package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.service.CoffeeAdminServiceInterface;
import com.tabram.coffeemaker.service.CoffeeUserServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/add-coffee-form")
@Controller
public class CoffeeAdminController {


    private final CoffeeAdminServiceInterface coffeeAdminServiceInterface;
    private final CoffeeUserServiceInterface coffeeUserServiceInterface;

    public CoffeeAdminController(CoffeeAdminServiceInterface coffeeAdminServiceInterface, CoffeeUserServiceInterface coffeeUserServiceInterface) {
        this.coffeeAdminServiceInterface = coffeeAdminServiceInterface;
        this.coffeeUserServiceInterface = coffeeUserServiceInterface;
    }


    @ModelAttribute("coffeeAdmin")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping
    public String showAddCoffeeForm() {
        return "add-coffee-form";
    }


    @PostMapping
    public String addCoffee(@ModelAttribute("coffeeAdmin") CoffeeDto coffeeDto) {
        coffeeAdminServiceInterface.addNewCoffee(coffeeDto);
        coffeeUserServiceInterface.addOneCoffeeForEachUser(coffeeDto);
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

