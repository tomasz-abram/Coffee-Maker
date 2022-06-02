package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;

public interface CoffeeAdminServiceInterface {
    CoffeeAdmin addNewCoffee(CoffeeDto coffeeDto);
}
