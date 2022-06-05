package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.service.CoffeeAdminServiceInterface;
import com.tabram.coffeemaker.service.CoffeeUserServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CoffeeAdminController {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeAdminServiceInterface coffeeAdminServiceInterface;
    private final CoffeeUserServiceInterface coffeeUserServiceInterface;

    public CoffeeAdminController(CoffeeAdminRepository coffeeAdminRepository, CoffeeAdminServiceInterface coffeeAdminServiceInterface, CoffeeUserServiceInterface coffeeUserServiceInterface) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.coffeeAdminServiceInterface = coffeeAdminServiceInterface;
        this.coffeeUserServiceInterface = coffeeUserServiceInterface;
    }


    @ModelAttribute("coffeeAdmin")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping("/add-coffee-form")
    public String showAddCoffeeForm() {
        return "admin-add-coffee-form";
    }


    @PostMapping("/add-coffee-form")
    public String addCoffee(@ModelAttribute("coffeeAdmin") CoffeeDto coffeeDto) {
        coffeeAdminServiceInterface.addNewCoffee(coffeeDto);
        coffeeUserServiceInterface.addOneCoffeeForEachUser(coffeeDto);
        return "redirect:/coffee-list";
    }

    @GetMapping("/admin-coffee-list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("admin-coffee-list");
        mav.addObject("coffees", coffeeAdminRepository.findAll());
        return mav;
    }

}

