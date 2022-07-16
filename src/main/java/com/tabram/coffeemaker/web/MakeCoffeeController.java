package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.MakeCoffeeService;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/make-coffee")
public class MakeCoffeeController {

    private final CoffeeUserService coffeeUserService;
    private final MakeCoffeeService makeCoffeeService;
    private final UserService userService;

    @Autowired
    public MakeCoffeeController(CoffeeUserService coffeeUserService, MakeCoffeeService makeCoffeeService, UserService userService) {
        this.coffeeUserService = coffeeUserService;
        this.makeCoffeeService = makeCoffeeService;
        this.userService = userService;
    }


    @GetMapping("/list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("make-coffee/list");
        mav.addObject("coffees", userService.findUserByName(userService.currentUser().getUsername()).getCoffeeUser());
        return mav;
    }

    @GetMapping("/{coffeeName}/strength")
    public ModelAndView chooseCoffee(@PathVariable String coffeeName) {
        ModelAndView mav = new ModelAndView("make-coffee/strength");
        CoffeeUser coffeeUser = coffeeUserService.findCoffeeByCoffeeNameAndUserId(coffeeName, userService.currentUser().getId());
        mav.addObject("chooseCoffee", coffeeUser);
        mav.addObject("tempCoffee", coffeeUserService.tempCoffee(coffeeUser));
        return mav;
    }

    @GetMapping("/{coffeeName}/{strength}/brew")
    public String brew(@PathVariable String coffeeName, @PathVariable int strength) {
        makeCoffeeService.makeCoffee(coffeeName, strength, userService.currentUser());
        return "redirect:/make-coffee/enjoy";
    }

    @GetMapping("/enjoy")
    public String enjoy() {
        return "make-coffee/enjoy";
    }

}
