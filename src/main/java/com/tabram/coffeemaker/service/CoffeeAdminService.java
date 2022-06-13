package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeAdminService {

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

    public CoffeeAdmin addNewCoffee(CoffeeDto coffeeDto) {

        CoffeeAdmin coffee = coffeeAdminRepository.findCoffeeAdminByNameOfCoffee(coffeeDto.getNameOfCoffee());
        if (coffee != null) {
            CoffeeAdmin coffeeDB = coffeeAdminRepository.findCoffeeAdminByNameOfCoffee(coffeeDto.getNameOfCoffee());
            coffeeDB.setTempWater(coffeeDto.getTempWater());
            coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
            coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
            coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
            coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
            coffeeDB.setCupSize(coffeeDB.getCupSize());
            return coffeeAdminRepository.save(coffeeDB);

        }
        CoffeeAdmin coffeeAdmin = new CoffeeAdmin(
                coffeeDto.getNameOfCoffee(),
                coffeeDto.getTempWater(),
                coffeeDto.getGrindingLevel(),
                coffeeDto.getAmountOfCoffee(),
                coffeeDto.getAmountOfWater(),
                coffeeDto.getAmountMilk(),
                coffeeDto.getTempMilk(),
                coffeeDto.getCupSize());
        return coffeeAdminRepository.save(coffeeAdmin);
    }
}
