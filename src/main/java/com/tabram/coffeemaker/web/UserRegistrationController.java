package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.service.CoffeeUserService;
import com.tabram.coffeemaker.service.UserService;
import com.tabram.coffeemaker.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserServiceInterface userServiceInterface;
    private final UserService userService;
    private final CoffeeUserService coffeeUserService;

    @Autowired
    public UserRegistrationController(UserServiceInterface userServiceInterface, UserService userService, CoffeeUserService coffeeUserService) {
        this.userServiceInterface = userServiceInterface;
        this.userService = userService;
        this.coffeeUserService = coffeeUserService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        if (!userService.checkIfTheUserExists(userRegistrationDto.getUserName())) {
            userServiceInterface.save(userRegistrationDto);
            coffeeUserService.addCoffeeListToUser(userService.findUserByName(userRegistrationDto.getUserName()));

            return "redirect:/login?regisSuccess";
        } else {
            return "redirect:/registration?regisError";
        }

    }

}

