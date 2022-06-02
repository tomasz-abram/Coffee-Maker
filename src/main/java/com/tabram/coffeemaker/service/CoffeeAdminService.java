package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoffeeAdminService implements CoffeeAdminServiceInterface {

    private final CoffeeAdminRepository coffeeAdminRepository;


    @Autowired
    public CoffeeAdminService(CoffeeAdminRepository coffeeAdminRepository) {
        this.coffeeAdminRepository = coffeeAdminRepository;

    }

    public List<CoffeeAdmin> getCoffee() {
        return coffeeAdminRepository.findAll();
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeAdminRepository.deleteById(coffeeId);

    }

    @Override
    public CoffeeAdmin addNewCoffee(CoffeeDto coffeeDto) {

        Optional<CoffeeAdmin> coffeeOptional = coffeeAdminRepository.findCoffeeByName(coffeeDto.getNameOfCoffee());
        if (coffeeOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }

        CoffeeAdmin coffeeAdmin = new CoffeeAdmin(
                coffeeDto.getNameOfCoffee(),
                coffeeDto.getTempWater(),
                coffeeDto.getGrindingLevel(),
                coffeeDto.getAmountOfCoffee(),
                coffeeDto.getAmountOfWater(),
                coffeeDto.getAmountMilk(),
                coffeeDto.getCupSize()
        );

       return coffeeAdminRepository.save(coffeeAdmin);
    }
}
