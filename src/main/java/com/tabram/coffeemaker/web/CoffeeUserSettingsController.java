package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoffeeUserSettingsController {


    private final UserRepository userRepository;
    private final CoffeeUserService coffeeUserService;
    private Authentication authentication;
    @Autowired
    public CoffeeUserSettingsController(UserRepository userRepository, CoffeeUserService coffeeUserService) {
        this.userRepository = userRepository;
        this.coffeeUserService = coffeeUserService;
    }


    @GetMapping("/user-coffee-settings")
    public ModelAndView getAllCoffees() {
        String currentUserName = null;
        ModelAndView mav = new ModelAndView("user-coffee-settings");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        mav.addObject("coffees", userRepository.findByUserName(currentUserName).getCoffeeUser());
        return mav;
    }

    @GetMapping("/delete-user-coffee")
    public String deleteUserCoffee(@RequestParam Long coffeeId) {
        coffeeUserService.deleteCoffee(coffeeId);
        return "redirect:/user-coffee-settings";
    }
}
