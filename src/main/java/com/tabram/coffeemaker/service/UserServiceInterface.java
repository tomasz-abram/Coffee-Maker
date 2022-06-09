package com.tabram.coffeemaker.service;


import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
