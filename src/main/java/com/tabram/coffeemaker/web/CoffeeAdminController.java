package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CoffeeAdminController {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeAdminService coffeeAdminService;
    private final CoffeeUserService coffeeUserService;


    public CoffeeAdminController(CoffeeAdminRepository coffeeAdminRepository, CoffeeAdminService coffeeAdminService, CoffeeUserService coffeeUserService) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.coffeeAdminService = coffeeAdminService;
        this.coffeeUserService = coffeeUserService;
    }


    @ModelAttribute("coffeeAdmin")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping("/admin-add-coffee")
    public String showAddCoffeeForm() {
        return "admin-add-coffee";
    }


    @PostMapping("/admin-add-coffee")
    public String addCoffee(@ModelAttribute("coffeeAdmin") CoffeeDto coffeeDto) {
        coffeeAdminService.addNewCoffee(coffeeDto);
        coffeeUserService.addOneCoffeeForEachUser(coffeeDto);
        return "redirect:/coffee-list";
    }

    @GetMapping("admin-coffee-list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("admin-coffee-list");
        mav.addObject("coffees", coffeeAdminRepository.findAll());
        return mav;
    }


}

