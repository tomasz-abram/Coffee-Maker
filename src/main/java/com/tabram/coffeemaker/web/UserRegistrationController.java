package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserRegistrationController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        String userName = userRegistrationDto.getUserName();
        if (userRepository.findByUserName(userRegistrationDto.getUserName()) == null) {
            userService.save(userRegistrationDto);
            return "redirect:/login?regisSuccess";
        } else {
            return "redirect:/registration?regisError";
        }
    }


}

