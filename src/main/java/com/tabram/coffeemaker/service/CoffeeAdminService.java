package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.exception.CoffeeAdminAlreadyExistsException;
import com.tabram.coffeemaker.exception.CoffeeAdminNotFoundException;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoffeeAdminService {

    private final CoffeeAdminRepository coffeeAdminRepository;


    @Autowired
    public CoffeeAdminService(CoffeeAdminRepository coffeeAdminRepository) {
        this.coffeeAdminRepository = coffeeAdminRepository;
    }

    public List<CoffeeAdmin> getAllCoffees() {
        return coffeeAdminRepository.findAll();
    }

    public CoffeeAdmin findCoffeeAdminByCoffeeName(String coffeeName) {
        return coffeeAdminRepository.findCoffeeAdminByCoffeeName(coffeeName).orElseThrow(() -> new CoffeeAdminNotFoundException("Coffee Admin not found."));
    }

    public void deleteCoffeeByCoffeeName(String coffeeName) {
        coffeeAdminRepository.deleteCoffeeAdminByCoffeeName(coffeeName);
    }

    public boolean coffeeExists(String coffeeName) {
        return coffeeAdminRepository.existsCoffeeAdminByCoffeeName(coffeeName);
    }

    public CoffeeAdmin addNewCoffee(CoffeeDto coffeeDto) {
        if (coffeeExists(coffeeDto.getCoffeeName())) {
            throw new CoffeeAdminAlreadyExistsException("Coffee admin already exists.");
        } else {
            CoffeeAdmin coffeeAdmin = new CoffeeAdmin(
                    coffeeDto.getCoffeeName(),
                    coffeeDto.getTempWater(),
                    coffeeDto.getGrindingLevel(),
                    coffeeDto.getAmountOfCoffee(),
                    coffeeDto.getAmountOfWater(),
                    coffeeDto.getAmountMilk(),
                    coffeeDto.getTempMilk(),
                    coffeeDto.getCupSize());
            coffeeAdminRepository.save(coffeeAdmin);
            return coffeeAdmin;
        }
    }

    public CoffeeAdmin updateAdminCoffee(CoffeeDto coffeeDto) {
        CoffeeAdmin coffeeDB = findCoffeeAdminByCoffeeName(coffeeDto.getCoffeeName());
        coffeeDB.setTempWater(coffeeDto.getTempWater());
        coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
        coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
        coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
        coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
        coffeeDB.setCupSize(coffeeDto.getCupSize());
        coffeeAdminRepository.save(coffeeDB);
        return coffeeDB;
    }
}