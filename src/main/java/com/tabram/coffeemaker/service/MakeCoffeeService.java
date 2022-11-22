package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeMachineStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MakeCoffeeService {

    private final CoffeeMachineStockRepository coffeeMachineStockRepository;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;
    private final CoffeeMachineStockService coffeeMachineStockService;
    private final CoffeeUserService coffeeUserService;

    @Autowired
    public MakeCoffeeService(CoffeeMachineStockRepository coffeeMachineStockRepository, CoffeeMachineConstantValueService coffeeMachineConstantValueService, CoffeeMachineStockService coffeeMachineStockService, CoffeeUserService coffeeUserService) {
        this.coffeeMachineStockRepository = coffeeMachineStockRepository;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
        this.coffeeMachineStockService = coffeeMachineStockService;
        this.coffeeUserService = coffeeUserService;
    }

    @Transactional
    public void makeCoffee(String coffeeName, int quantity, User user) {
        List<CoffeeMachineStock> coffeeMachineStocksList = new ArrayList<>();
        CoffeeMachineStock waterStock = coffeeMachineStockService.findStockByName("Water");
        CoffeeMachineStock milkStock = coffeeMachineStockService.findStockByName("Milk");
        CoffeeMachineStock coffeeBeansStock = coffeeMachineStockService.findStockByName("Coffee beans");
        CoffeeMachineStock groundContainerStock = coffeeMachineStockService.findStockByName("Ground container");
        CoffeeMachineStock descaleCounter = coffeeMachineStockService.findStockByName("Descale counter");
        CoffeeMachineStock waterHardnessStock = coffeeMachineStockService.findStockByName("Water hardness");

        CoffeeUser coffee = coffeeUserService.findCoffeeByCoffeeNameAndUsername(coffeeName, user.getId());
        if (waterStock.getValue() < coffee.getAmountOfWater() * quantity) {
            throw new IllegalStateException("There is not enough water in the machine to make this coffee");
        } else {
            waterStock.setValue(waterStock.getValue() - coffee.getAmountOfWater() * quantity);
            coffeeMachineStocksList.add(waterStock);
        }

        if (milkStock.getValue() < coffee.getAmountMilk() * quantity) {
            throw new IllegalStateException("There is not enough milk in the machine to make this coffee");
        } else {
            milkStock.setValue(milkStock.getValue() - coffee.getAmountMilk() * quantity);
            coffeeMachineStocksList.add(milkStock);
        }

        if (coffeeBeansStock.getValue() < coffee.getAmountOfCoffee() * quantity) {
            throw new IllegalStateException("There is not enough coffee beans in the machine to make this coffee");
        } else {
            coffeeBeansStock.setValue((float) (coffeeBeansStock.getValue() - coffee.getAmountOfCoffee() * quantity));
            coffeeMachineStocksList.add(coffeeBeansStock);
        }

        if (coffeeMachineConstantValueService.getMaxGroundContainer() < groundContainerStock.getValue() + quantity) {
            throw new IllegalStateException("Empty the grounds container before making this coffee");
        } else {
            groundContainerStock.setValue(groundContainerStock.getValue() + quantity);
            coffeeMachineStocksList.add(groundContainerStock);
        }

        if (coffeeMachineConstantValueService.getMaxDescaleCounter() < descaleCounter.getValue() + waterHardnessStock.getValue() * coffee.getAmountOfWater() * quantity) {
            throw new IllegalStateException("Descale the coffee machine before making this coffee");
        } else {
            descaleCounter.setValue((int) (descaleCounter.getValue() + waterHardnessStock.getValue() * coffee.getAmountOfWater() * quantity));
            coffeeMachineStocksList.add(descaleCounter);
        }


        coffeeMachineStockRepository.saveAll(coffeeMachineStocksList);
    }

}
