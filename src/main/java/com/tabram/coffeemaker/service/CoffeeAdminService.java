package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeDto;
import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.model.CoffeeAdmin;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeAdminRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeAdminService {

    private final CoffeeAdminRepository coffeeAdminRepository;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    private final UserRepository userRepository;


    @Autowired
    public CoffeeAdminService(CoffeeAdminRepository coffeeAdminRepository, CoffeeMachineConstantValueService coffeeMachineConstantValueService, UserRepository userRepository) {
        this.coffeeAdminRepository = coffeeAdminRepository;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
        this.userRepository = userRepository;
    }

    public void checkCoffeeParameters(CoffeeDto coffeeDto) {
        if (coffeeDto.getNameOfCoffee().isEmpty()) {
            throw new IllegalArgumentException("The name can not be empty.");
        }
        if (coffeeDto.getTempWater() < coffeeMachineConstantValueService.getMinTempWater() || coffeeDto.getTempWater() > coffeeMachineConstantValueService.getMaxTempWater()) {
            throw new IllegalArgumentException("The water temperature must be in the range " + coffeeMachineConstantValueService.getMinTempWater() + " - " + coffeeMachineConstantValueService.getMaxTempWater());
        }
        if (coffeeDto.getGrindingLevel() < coffeeMachineConstantValueService.getMinGrindingLevel() || coffeeDto.getGrindingLevel() > coffeeMachineConstantValueService.getMaxGrindingLevel()) {
            throw new IllegalArgumentException("The grinding level must be in the range " + coffeeMachineConstantValueService.getMinGrindingLevel() + " - " + coffeeMachineConstantValueService.getMaxGrindingLevel());
        }
        if (coffeeDto.getAmountOfCoffee() < coffeeMachineConstantValueService.getMinAmountOfCoffee() || coffeeDto.getAmountOfCoffee() > coffeeMachineConstantValueService.getMaxAmountOfCoffee()) {
            throw new IllegalArgumentException("The amount of coffee must be in the range " + coffeeMachineConstantValueService.getMinAmountOfCoffee() + " - " + coffeeMachineConstantValueService.getMaxAmountOfCoffee());
        }
        if (coffeeDto.getAmountOfWater() < coffeeMachineConstantValueService.getMinAmountOfWater() || coffeeDto.getAmountOfWater() > coffeeMachineConstantValueService.getMaxAmountOfWater()) {
            throw new IllegalArgumentException("The amount of water must be in the range " + coffeeMachineConstantValueService.getMinAmountOfWater() + " - " + coffeeMachineConstantValueService.getMaxAmountOfWater());
        }
        if (coffeeDto.getAmountMilk() < coffeeMachineConstantValueService.getMinAmountOfMilk() || coffeeDto.getAmountMilk() > coffeeMachineConstantValueService.getMaxAmountOfMilk()) {
            throw new IllegalArgumentException("The amount milk must be in the range " + coffeeMachineConstantValueService.getMinAmountOfMilk() + " - " + coffeeMachineConstantValueService.getMaxAmountOfMilk());
        }
        if (coffeeDto.getTempMilk() < coffeeMachineConstantValueService.getMinTempMilk() || coffeeDto.getTempMilk() > coffeeMachineConstantValueService.getMaxTempMilk()) {
            throw new IllegalArgumentException("The temp. milk must be in the range " + coffeeMachineConstantValueService.getMinTempMilk() + " - " + coffeeMachineConstantValueService.getMaxTempMilk());
        }
        if (coffeeDto.getCupSize() < coffeeMachineConstantValueService.getMinCupSize() || coffeeDto.getCupSize() > coffeeMachineConstantValueService.getMaxCupSize()) {
            throw new IllegalArgumentException("The cup size must be in the range " + coffeeMachineConstantValueService.getMinCupSize() + " - " + coffeeMachineConstantValueService.getMaxCupSize());
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

    public void addNewCoffee(CoffeeDto coffeeDto) {

        checkCoffeeParameters(coffeeDto);

        CoffeeAdmin coffee = coffeeAdminRepository.findCoffeeAdminByNameOfCoffee(coffeeDto.getNameOfCoffee());
        if (coffee != null) {
            CoffeeAdmin coffeeDB = coffeeAdminRepository.findCoffeeAdminByNameOfCoffee(coffeeDto.getNameOfCoffee());
            coffeeDB.setTempWater(coffeeDto.getTempWater());
            coffeeDB.setGrindingLevel(coffeeDto.getGrindingLevel());
            coffeeDB.setAmountOfCoffee(coffeeDto.getAmountOfCoffee());
            coffeeDB.setAmountOfWater(coffeeDto.getAmountOfWater());
            coffeeDB.setAmountMilk(coffeeDto.getAmountMilk());
            coffeeDB.setCupSize(coffeeDto.getCupSize());
            coffeeAdminRepository.save(coffeeDB);
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
        coffeeAdminRepository.save(coffeeAdmin);
    }

    public void updateUser(UserDto userDto) {
        User userDB = userRepository.findByUserName(userDto.getUsername());
        userDB.setRoles(userDto.getRoles());
        userDB.setEnabled(userDto.isEnabled());
        userRepository.save(userDB);
    }
}