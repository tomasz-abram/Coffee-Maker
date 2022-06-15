package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.config.CoffeeMachine;
import com.tabram.coffeemaker.model.CoffeeMachineStatus;
import com.tabram.coffeemaker.repository.CoffeeMachineStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CoffeeMachineStatusService {


    private final CoffeeMachineStatusRepository coffeeMachineStatusRepository;
    private final CoffeeMachine coffeeMachine;
    Long machineId = 1L;

    @Autowired
    public CoffeeMachineStatusService(CoffeeMachineStatusRepository coffeeMachineStatusRepository, CoffeeMachine coffeeMachine) {
        this.coffeeMachineStatusRepository = coffeeMachineStatusRepository;
        this.coffeeMachine = coffeeMachine;
    }


    public void updateWater(int addWater) {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);

        int updateWater = Objects.requireNonNull(machine).getWaterLevel() + addWater;

        if (updateWater > coffeeMachine.getMAX_WATER_CONTAINER()) {
            machine.setWaterLevel(coffeeMachine.getMAX_WATER_CONTAINER());
        } else {
            machine.setWaterLevel(updateWater);
        }
        coffeeMachineStatusRepository.save(machine);
    }

    public void emptyWater() {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        Objects.requireNonNull(machine).setWaterLevel(0);
        coffeeMachineStatusRepository.save(machine);
    }


    public void updateMilk(int addMilk) {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        int updateMilk = Objects.requireNonNull(machine).getMilkLevel() + addMilk;

        if (updateMilk > coffeeMachine.getMAX_MILK_CONTAINER()) {
            machine.setMilkLevel(coffeeMachine.getMAX_MILK_CONTAINER());
        } else {
            machine.setMilkLevel(updateMilk);
        }
        coffeeMachineStatusRepository.save(machine);
    }

    public void emptyMilk() {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        Objects.requireNonNull(machine).setMilkLevel(0);
        coffeeMachineStatusRepository.save(machine);
    }

    public void updateBeans(int addBeans) {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        float updateBeans = Objects.requireNonNull(machine).getCoffeeBeansLevel() + addBeans;

        if (updateBeans > coffeeMachine.getMAX_COFFEE_BEANS_CONTAINER()) {
            machine.setCoffeeBeansLevel(coffeeMachine.getMAX_COFFEE_BEANS_CONTAINER());
        } else {
            machine.setCoffeeBeansLevel(updateBeans);
        }
        coffeeMachineStatusRepository.save(machine);
    }

    public void emptyCoffeeBeans() {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        Objects.requireNonNull(machine).setCoffeeBeansLevel(0);
        coffeeMachineStatusRepository.save(machine);
    }

    public void emptyGroundContainer() {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        Objects.requireNonNull(machine).setGroundContainerLevel(0);
        coffeeMachineStatusRepository.save(machine);
    }

    public void descale() {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        Objects.requireNonNull(machine).setDescaleCounter(0);
        coffeeMachineStatusRepository.save(machine);
    }

    public void updateWaterHardness(CoffeeMachineStatus cMS) {
        CoffeeMachineStatus machine = coffeeMachineStatusRepository.findById(machineId).orElse(null);
        if (cMS.getWaterHardness() < 0) {
            throw new IllegalStateException("The water hardness must not be less than zero");
        }
        if (cMS.getWaterHardness() > 100) {
            throw new IllegalStateException("If you don't pour concrete over your coffee, this value is probably lower :)");
        }
        Objects.requireNonNull(machine).setWaterHardness(cMS.getWaterHardness());
        coffeeMachineStatusRepository.save(machine);
    }
}
