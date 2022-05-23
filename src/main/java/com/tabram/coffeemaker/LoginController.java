package com.tabram.coffeemaker;

import com.tabram.coffeemaker.user.User;
import com.tabram.coffeemaker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}



