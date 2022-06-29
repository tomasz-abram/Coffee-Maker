package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.config.CoffeeMachine;
import com.tabram.coffeemaker.model.CoffeeMachineStatus;
import com.tabram.coffeemaker.model.CoffeeUser;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.CoffeeMachineStatusRepository;
import com.tabram.coffeemaker.repository.CoffeeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeCoffeeService {

    private final CoffeeMachineStatusRepository coffeeMachineStatusRepository;
    private final CoffeeUserRepository coffeeUserRepository;
    private final CoffeeMachine coffeeMachine;


    @Autowired
    public MakeCoffeeService(CoffeeMachineStatusRepository coffeeMachineStatusRepository, CoffeeUserRepository coffeeUserRepository, CoffeeMachine coffeeMachine) {
        this.coffeeMachineStatusRepository = coffeeMachineStatusRepository;
        this.coffeeUserRepository = coffeeUserRepository;
        this.coffeeMachine = coffeeMachine;
    }

    public void makeCoffee(String nameOfCoffee, int quantity, User user) {
        CoffeeMachineStatus machineStatusRep = coffeeMachineStatusRepository.findById(coffeeMachine.getMACHINE_ID()).orElse(null);
        CoffeeUser coffee = coffeeUserRepository.findCoffeeUserByNameOfCoffeeAndUserId(nameOfCoffee, user.getId());

        if (machineStatusRep.getWaterLevel() < coffee.getAmountOfWater()) {
            throw new IllegalStateException("There is not enough water in the machine to make this coffee");
        } else {
            machineStatusRep.setWaterLevel(machineStatusRep.getWaterLevel() - coffee.getAmountOfWater());
        }

        if (machineStatusRep.getMilkLevel() < coffee.getAmountMilk()) {
            throw new IllegalStateException("There is not enough milk in the machine to make this coffee");
        } else {
            machineStatusRep.setMilkLevel(machineStatusRep.getMilkLevel() - coffee.getAmountMilk());
        }

        if (machineStatusRep.getCoffeeBeansLevel() < coffee.getAmountOfCoffee() * quantity) {
            throw new IllegalStateException("There is not enough coffee beans in the machine to make this coffee");
        } else {
            machineStatusRep.setCoffeeBeansLevel((float) (machineStatusRep.getCoffeeBeansLevel() - coffee.getAmountOfCoffee() * quantity));
        }

        if (coffeeMachine.getMAX_GROUND_CONTAINER() < machineStatusRep.getGroundContainerLevel() + quantity) {
            throw new IllegalStateException("Empty the grounds container before making this coffee");
        } else {
            machineStatusRep.setGroundContainerLevel(machineStatusRep.getGroundContainerLevel() + quantity);
        }

        if (coffeeMachine.getMAX_DESCALE_COUNTER() < machineStatusRep.getDescaleCounter() + machineStatusRep.getWaterHardness() * coffee.getAmountOfWater()) {
            throw new IllegalStateException("Descale the coffee machine before making this coffee");
        } else {
            machineStatusRep.setDescaleCounter((int) (machineStatusRep.getDescaleCounter() + machineStatusRep.getWaterHardness() * coffee.getAmountOfWater()));
        }

        coffeeMachineStatusRepository.save(machineStatusRep);
    }


    public void checkMakeCoffee(String nameOfCoffee, int quantity) {




    }
}
