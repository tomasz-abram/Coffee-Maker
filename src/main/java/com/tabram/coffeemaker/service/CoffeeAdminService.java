package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.config.CoffeeMachine;
import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeAdminService {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeMachine coffeeMachine;

    @Autowired
    public CoffeeAdminService(CoffeeAdminRepository coffeeAdminRepository, CoffeeMachine coffeeMachine) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.coffeeMachine = coffeeMachine;
    }

    public void checkCoffeeParameters(CoffeeDto coffeeDto, CoffeeMachine coffeeMachine) {
        if (coffeeDto.getNameOfCoffee().isEmpty()) {
            throw new IllegalArgumentException("The name can not be empty.");
        }
        if (coffeeDto.getTempWater() < coffeeMachine.getMIN_TEMP_WATER() || coffeeDto.getTempWater() > coffeeMachine.getMAX_TEMP_WATER()) {
            throw new IllegalArgumentException("The water temperature must be in the range " + coffeeMachine.getMIN_TEMP_WATER() + " - " + coffeeMachine.getMAX_TEMP_WATER());
        }
        if (coffeeDto.getGrindingLevel() < coffeeMachine.getMIN_GRINDING_LEVEL() || coffeeDto.getGrindingLevel() > coffeeMachine.getMAX_GRINDING_LEVEL()) {
            throw new IllegalArgumentException("The grinding level must be in the range " + coffeeMachine.getMIN_GRINDING_LEVEL() + " - " + coffeeMachine.getMAX_GRINDING_LEVEL());
        }
        if (coffeeDto.getAmountOfCoffee() < coffeeMachine.getMIN_AMOUNT_OF_COFFEE() || coffeeDto.getAmountOfCoffee() > coffeeMachine.getMAX_AMOUNT_OF_COFFEE()) {
            throw new IllegalArgumentException("The amount of coffee must be in the range " + coffeeMachine.getMIN_AMOUNT_OF_COFFEE() + " - " + coffeeMachine.getMAX_AMOUNT_OF_COFFEE());
        }
        if (coffeeDto.getAmountOfWater() < coffeeMachine.getMIN_AMOUNT_OF_WATER() || coffeeDto.getAmountOfWater() > coffeeMachine.getMAX_AMOUNT_OF_WATER()) {
            throw new IllegalArgumentException("The amount of water must be in the range " + coffeeMachine.getMIN_AMOUNT_OF_WATER() + " - " + coffeeMachine.getMAX_AMOUNT_OF_WATER());
        }
        if (coffeeDto.getAmountMilk() < coffeeMachine.getMIN_AMOUNT_OF_MILK() || coffeeDto.getAmountMilk() > coffeeMachine.getMAX_AMOUNT_OF_MILK()) {
            throw new IllegalArgumentException("The amount milk must be in the range " + coffeeMachine.getMIN_AMOUNT_OF_MILK() + " - " + coffeeMachine.getMAX_AMOUNT_OF_MILK());
        }
        if (coffeeDto.getTempMilk() < coffeeMachine.getMIN_TEMP_MILK() || coffeeDto.getTempMilk() > coffeeMachine.getMAX_TEMP_MILK()) {
            throw new IllegalArgumentException("The temp. milk must be in the range " + coffeeMachine.getMIN_TEMP_MILK() + " - " + coffeeMachine.getMAX_TEMP_MILK());
        }
        if (coffeeDto.getCupSize() < coffeeMachine.getMIN_CUP_SIZE() || coffeeDto.getCupSize() > coffeeMachine.getMAX_CUP_SIZE()) {
            throw new IllegalArgumentException("The cup size must be in the range " + coffeeMachine.getMIN_CUP_SIZE() + " - " + coffeeMachine.getMAX_CUP_SIZE());
        }
        if (coffeeDto.getAmountOfWater() + coffeeDto.getAmountMilk() > coffeeDto.getCupSize()) {
            throw new IllegalArgumentException("The cup must not be smaller than the sum of the amounts of water and milk.");
        }
    }

    public List<CoffeeAdmin> getCoffee() {
        return coffeeAdminRepository.findAll();
    }

    public void deleteCoffee(Long coffeeId) {
        coffeeAdminRepository.deleteById(coffeeId);

    }

    public CoffeeAdmin addNewCoffee(CoffeeDto coffeeDto) {

        checkCoffeeParameters(coffeeDto, coffeeMachine);

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
