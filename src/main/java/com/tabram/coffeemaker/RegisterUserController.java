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
public class RegisterUserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register-user")
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView("register-user");
        User newUser = new User();
        newUser.setRoles("User");
        newUser.setActive(true);
        mav.addObject("user", newUser);

        return mav;
    }


    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/";
    }
}

