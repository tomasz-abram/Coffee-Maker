package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CoffeeAdminController {

    private final CoffeeAdminService coffeeAdminService;
    private final CoffeeUserService coffeeUserService;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    private final UserService userService;

    @Autowired
    public CoffeeAdminController(CoffeeAdminService coffeeAdminService, CoffeeUserService coffeeUserService, CoffeeMachineConstantValueService coffeeMachineConstantValueService, UserService userService) {
        this.coffeeAdminService = coffeeAdminService;
        this.coffeeUserService = coffeeUserService;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
        this.userService = userService;
    }

    @ModelAttribute("coffeeAdmin")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping("/admin/admin-add-coffee")
    public ModelAndView showAddCoffeeForm() {
        ModelAndView mav = new ModelAndView("admin/admin-add-coffee");
        mav.addObject("coffeeMachine", coffeeMachineConstantValueService);
        return mav;
    }

    @PostMapping("/admin/admin-add-coffee")
    public String addCoffee(@ModelAttribute("coffeeAdmin") CoffeeDto coffeeDto) {
        coffeeAdminService.checkCoffeeParameters(coffeeDto);
        coffeeAdminService.addNewCoffee(coffeeDto);
        coffeeUserService.addOneCoffeeForEachUser(coffeeDto);
        coffeeUserService.updateDefaultCoffees(userService.findUserByName("Default"));
        return "redirect:/admin/admin-coffee-list";
    }

    @GetMapping("/admin/show-update-form")
    public ModelAndView showUpdateForm(@RequestParam Long coffeeAdminId) {
        ModelAndView mav = new ModelAndView("admin/admin-add-coffee");
        mav.addObject("coffeeAdmin", coffeeAdminService.findCoffeeById(coffeeAdminId));
        mav.addObject("coffeeMachine", coffeeMachineConstantValueService);
        return mav;
    }

    @GetMapping("admin/admin-coffee-list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("admin/admin-coffee-list");
        mav.addObject("coffees", coffeeAdminService.getAllCoffees());
        return mav;
    }

    @DeleteMapping("/admin/delete-admin-coffee")
    public String deleteAdminCoffee(@RequestParam Long coffeeAdminId) {
        coffeeAdminService.deleteCoffee(coffeeAdminId);
        return "redirect:/admin/admin-coffee-list";
    }
}

