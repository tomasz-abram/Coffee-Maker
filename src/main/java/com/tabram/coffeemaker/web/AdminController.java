package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

//    @PostUpdate("/admin-users-list")
//    public String deactivationUser(@RequestParam Long userId) {
//        leteCoffee(userId);
//        return "redirect:/user-coffee-settings";
//    }

    @GetMapping(path = {"/deactivationUser"})
    public String deactivationUser(
            @RequestParam Long userId) {
        userService.deactivationUser(userId);
        return "redirect:/admin-users-list";
    }

    @GetMapping("admin-users-list")
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("admin-users-list");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }
}
