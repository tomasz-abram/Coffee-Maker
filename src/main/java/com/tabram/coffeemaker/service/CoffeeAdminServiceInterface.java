package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeAdminDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;

public interface CoffeeAdminServiceInterface {
    CoffeeAdmin saveCoffee(CoffeeAdminDto coffeeAdminDto);
}
