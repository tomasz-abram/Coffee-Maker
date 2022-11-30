package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CoffeeAdminService {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;


    @Autowired
    public CoffeeAdminService(CoffeeAdminRepository coffeeAdminRepository, CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    public void checkCoffeeParameters(CoffeeDto coffeeDto) {
        if (coffeeDto.getCoffeeName().isEmpty()) {
            throw new IllegalArgumentException("The name can not be empty.");
        }
        if (!isBetween(coffeeDto.getTempWater(), coffeeMachineConstantValueService.getMinTempWater(), coffeeMachineConstantValueService.getMaxTempWater())) {
            throw new IllegalArgumentException("The water temperature must be in the range " + coffeeMachineConstantValueService.getMinTempWater() + " - " + coffeeMachineConstantValueService.getMaxTempWater());
        }
        if (!isBetween(coffeeDto.getGrindingLevel(), coffeeMachineConstantValueService.getMinGrindingLevel(), coffeeMachineConstantValueService.getMaxGrindingLevel())) {
            throw new IllegalArgumentException("The grinding level must be in the range " + coffeeMachineConstantValueService.getMinGrindingLevel() + " - " + coffeeMachineConstantValueService.getMaxGrindingLevel());
        }
        if (!isBetween(coffeeDto.getAmountOfCoffee(), coffeeMachineConstantValueService.getMinAmountOfCoffee(), coffeeMachineConstantValueService.getMaxAmountOfCoffee())) {
            throw new IllegalArgumentException("The amount of coffee must be in the range " + coffeeMachineConstantValueService.getMinAmountOfCoffee() + " - " + coffeeMachineConstantValueService.getMaxAmountOfCoffee());
        }
        if (!isBetween(coffeeDto.getAmountOfWater(), coffeeMachineConstantValueService.getMinAmountOfWater(), coffeeMachineConstantValueService.getMaxAmountOfWater())) {
            throw new IllegalArgumentException("The amount of water must be in the range " + coffeeMachineConstantValueService.getMinAmountOfWater() + " - " + coffeeMachineConstantValueService.getMaxAmountOfWater());
        }
        if (!isBetween(coffeeDto.getAmountMilk(), coffeeMachineConstantValueService.getMinAmountOfMilk(), coffeeMachineConstantValueService.getMaxAmountOfMilk())) {
            throw new IllegalArgumentException("The amount milk must be in the range " + coffeeMachineConstantValueService.getMinAmountOfMilk() + " - " + coffeeMachineConstantValueService.getMaxAmountOfMilk());
        }
        if (!isBetween(coffeeDto.getTempMilk(), coffeeMachineConstantValueService.getMinTempMilk(), coffeeMachineConstantValueService.getMaxTempMilk())) {
            throw new IllegalArgumentException("The temp. milk must be in the range " + coffeeMachineConstantValueService.getMinTempMilk() + " - " + coffeeMachineConstantValueService.getMaxTempMilk());
        }
        if (!isBetween(coffeeDto.getCupSize(), coffeeMachineConstantValueService.getMinCupSize(), coffeeMachineConstantValueService.getMaxCupSize())) {
            throw new IllegalArgumentException("The cup size must be in the range " + coffeeMachineConstantValueService.getMinCupSize() + " - " + coffeeMachineConstantValueService.getMaxCupSize());
        }
        if (coffeeDto.getAmountOfWater() + coffeeDto.getAmountMilk() > coffeeDto.getCupSize()) {
            throw new IllegalArgumentException("The cup must not be smaller than the sum of the amounts of water and milk.");
        }
    }

    public boolean isBetween(double val, int min, int max) {
        return val >= min && val <= max;
    }

    public List<CoffeeAdmin> getAllCoffees() {
        return coffeeAdminRepository.findAll();
    }

    public CoffeeAdmin findCoffeeById(Long coffeeId) {
        return coffeeAdminRepository.findById(coffeeId)
                .orElseThrow(() -> new EntityNotFoundException("Admin: coffee not found"));
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeAdminRepository.deleteById(coffeeId);
    }

    public void addNewCoffee(CoffeeDto coffeeDto) {
        if (coffeeAdminRepository.existsCoffeeAdminByCoffeeName(coffeeDto.getCoffeeName())) {
            CoffeeAdmin coffeeDB = coffeeAdminRepository.findCoffeeAdminByCoffeeName(coffeeDto.getCoffeeName());
            coffeeDB.setTempWater(coffeeDto.getTempWater());
            coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
            coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
            coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
            coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
            coffeeDB.setCupSize(coffeeDto.getCupSize());
            coffeeAdminRepository.save(coffeeDB);
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
        }

    }
}