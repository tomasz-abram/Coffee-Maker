package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.MakeCoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/make-coffee")
public class MakeCoffeeController {

    private final UserRepository userRepository;
    private final CoffeeUserService coffeeUserService;
    private final CoffeeUserRepository coffeeUserRepository;
    private final MakeCoffeeService makeCoffeeService;

    @Autowired
    public MakeCoffeeController(UserRepository userRepository, CoffeeUserService coffeeUserService, CoffeeUserRepository coffeeUserRepository, MakeCoffeeService makeCoffeeService) {
        this.userRepository = userRepository;
        this.coffeeUserService = coffeeUserService;
        this.coffeeUserRepository = coffeeUserRepository;
        this.makeCoffeeService = makeCoffeeService;
    }


    @GetMapping("/list")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("make-coffee/list");
        mav.addObject("coffees", userRepository.findByUserName(coffeeUserService.currentUser().getUsername()).getCoffeeUser());
        return mav;
    }

    @GetMapping("/{coffeeName}/strength")
    public ModelAndView chooseCoffee(@PathVariable String coffeeName) {
        ModelAndView mav = new ModelAndView("make-coffee/strength");
        CoffeeUser coffeeUser = coffeeUserRepository.findCoffeeUserByNameOfCoffeeAndUserId(coffeeName, coffeeUserService.currentUser().getId());
        mav.addObject("chooseCoffee", coffeeUser);
        mav.addObject("tempCoffee", coffeeUserService.tempCoffee(coffeeUser));
        return mav;
    }

    @GetMapping("/{coffeeName}/{strength}/brew")
    public String brew(@PathVariable String coffeeName, @PathVariable int strength) {
        makeCoffeeService.makeCoffee(coffeeName, strength, coffeeUserService.currentUser());
        return "redirect:/make-coffee/enjoy";
    }

    @GetMapping("/enjoy")
    public String enjoy() {
        return "make-coffee/enjoy";
    }

}
