package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoffeeUserController {


    private final UserService userService;
    private final CoffeeUserService coffeeUserService;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    @Autowired
    public CoffeeUserController(UserService userService, CoffeeUserService coffeeUserService, CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.userService = userService;
        this.coffeeUserService = coffeeUserService;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @ModelAttribute("coffeeUser")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping("/user/user-add-coffee")
    public ModelAndView showAddCoffeeForm() {
        ModelAndView mav = new ModelAndView("user/user-add-coffee");
        mav.addObject("coffeeMachine", coffeeMachineConstantValueService);
        return mav;
    }

    @PostMapping("/user/user-add-coffee")
    public String addCoffee(@ModelAttribute("coffeeUser") CoffeeDto coffeeDto) {
        coffeeUserService.saveCoffee(coffeeDto, userService.currentUser());
        return "redirect:/coffee-settings";
    }

    @GetMapping("/coffee-settings")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("coffee-settings");
        mav.addObject("coffees", userService.findUserByName(userService.currentUser().getUsername()).getCoffeeUser());
        return mav;
    }

    @GetMapping("/user/show-update-form")
    public ModelAndView showUpdateForm(@RequestParam Long coffeeUserId) {
        ModelAndView mav = new ModelAndView("user/user-add-coffee");
        mav.addObject("coffeeUser", coffeeUserService.findCoffeeUserById(coffeeUserId));
        mav.addObject("coffeeMachine", coffeeMachineConstantValueService);
        return mav;
    }

    @GetMapping("/user/delete-user-coffee")
    public String deleteUserCoffee(@RequestParam Long coffeeUserId) {
        coffeeUserService.deleteCoffee(coffeeUserId);
        return "redirect:/coffee-settings";
    }

    @GetMapping("/user/update-coffee-recipes")
    public String updateCoffeeRecipes() {
        coffeeUserService.updateDefaultCoffees(userService.currentUser());
        return "redirect:/coffee-settings";
    }
}
