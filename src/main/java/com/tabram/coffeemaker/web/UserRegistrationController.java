package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

// zamiast modelattribute i powyższego getMapping można zastosować
//    @GetMapping
//    public String showRegistrationForm(Model model){
//        model.addAttribute("user", new UserRegistrationDto());
//        return "registration";
//    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/?success";
    }
}
