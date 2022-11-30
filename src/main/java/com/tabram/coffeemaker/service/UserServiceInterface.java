package com.tabram.coffeemaker.service;


import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    User getUserByUsername(String username);

    List<User> getAllUsers();

    User saveUser(UserRegistrationDto registrationDto);

    void addRoleToUser(String username, String roleName);
}
