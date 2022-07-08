package com.tabram.coffeemaker.web;

import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.RoleRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CoffeeAdminService coffeeAdminService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository, CoffeeAdminService coffeeAdminService, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.coffeeAdminService = coffeeAdminService;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("userD")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping(path = {"/admin/deactivationUser"})
    public String deactivationUser(
            @RequestParam Long userId) {
        userService.deactivationUser(userId);
        return "redirect:/admin/admin-users-list";
    }

    @GetMapping("/admin/admin-users-list")
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("/admin/admin-users-list");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }

    @PostMapping("/admin/admin-update-user")
    public String updateUser(@ModelAttribute("userD") UserDto userDto) {
        coffeeAdminService.updateUser(userDto);
        return "redirect:/admin/admin-users-list";
    }

    @GetMapping("/admin/updateUserForm")
    public ModelAndView showUpdateForm(@RequestParam Long userId) {
        ModelAndView mav = new ModelAndView("/admin/admin-update-user");
        User user = userRepository.findById(userId).orElse(null);
        mav.addObject("userD", user);
//        List<Role> roles = roleRepository.findAll();
        mav.addObject("roles",  roleRepository.findAll());
        return mav;
    }
//
}
