package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeUser;

import java.util.List;

public interface CoffeeUserServiceInterface {
    List<CoffeeUser> addOneCoffeeForEachUser(CoffeeDto coffeeDto);
}
