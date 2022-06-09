package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoffeeUserController {


    private final UserRepository userRepository;
    private final CoffeeUserService coffeeUserService;
    private final CoffeeUserRepository coffeeUserRepository;

    @Autowired
    public CoffeeUserController(UserRepository userRepository, CoffeeUserService coffeeUserService, CoffeeUserRepository coffeeUserRepository) {
        this.userRepository = userRepository;
        this.coffeeUserService = coffeeUserService;
        this.coffeeUserRepository = coffeeUserRepository;
    }


    @ModelAttribute("coffeeUser")
    public CoffeeDto coffeeDto() {
        return new CoffeeDto();
    }

    @GetMapping("/user/user-add-coffee")
    public String showAddCoffeeForm() {
        return "user/user-add-coffee";
    }

    @PostMapping("/user/user-add-coffee")
    public String addCoffee(@ModelAttribute("coffeeUser") CoffeeDto coffeeDto) {
        coffeeUserService.addNewCoffee(coffeeDto, coffeeUserService.currentUser());
        return "redirect:/coffee-settings";
    }

    @GetMapping("/coffee-settings")
    public ModelAndView getAllCoffees() {
        ModelAndView mav = new ModelAndView("coffee-settings");
        mav.addObject("coffees", userRepository.findByUserName(coffeeUserService.currentUser().getUserName()).getCoffeeUser());
        return mav;
    }

    @GetMapping("/user/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long coffeeUserId) {
        ModelAndView mav = new ModelAndView("/user/user-add-coffee");
        CoffeeUser coffeeUser = coffeeUserRepository.findById(coffeeUserId).get();
        mav.addObject("coffeeUser", coffeeUser);
        return mav;
    }

    @GetMapping("/user/delete-user-coffee")
    public String deleteUserCoffee(@RequestParam Long coffeeId) {
        coffeeUserService.deleteCoffee(coffeeId);
        return "redirect:/user/user-coffee-settings";
    }
}
