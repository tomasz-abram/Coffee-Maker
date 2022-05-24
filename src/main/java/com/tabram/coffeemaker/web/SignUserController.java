package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.user.User;
import com.tabram.coffeemaker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class SignUserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }


    @GetMapping("/sign-up")
    public ModelAndView signUp(){
        ModelAndView mav = new ModelAndView("sign-up");
        User newUser = new User();
        newUser.setRoles("User");
        newUser.setActive(true);
        mav.addObject("user", newUser);

        return mav;
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/?success";
    }
}

