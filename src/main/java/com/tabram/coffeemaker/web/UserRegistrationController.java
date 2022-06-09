package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.repository.UserRepository;
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
    private final UserRepository userRepository;
    @Autowired
    public UserRegistrationController(UserServiceInterface userServiceInterface, UserRepository userRepository) {
        this.userServiceInterface = userServiceInterface;
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
            userServiceInterface.save(userRegistrationDto);
            return "redirect:/login?regisSuccess";
        } else {
            return "redirect:/registration?regisError";
        }
    }


}

