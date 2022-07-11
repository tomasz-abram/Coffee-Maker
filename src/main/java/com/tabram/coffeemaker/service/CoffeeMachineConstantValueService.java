package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeMachineConstantValueDto;
import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import com.tabram.coffeemaker.repository.CoffeeMachineConstantValueRepository;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineConstantValueService {

    private final CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository;

    public CoffeeMachineConstantValueService(CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository) {
        this.coffeeMachineConstantValueRepository = coffeeMachineConstantValueRepository;
    }

    public Integer getMinWaterContainer() {
        return coffeeMachineConstantValueRepository.findByName("min_water_container").getValue();
    }

    public Integer getMaxWaterContainer() {
        return coffeeMachineConstantValueRepository.findByName("max_water_container").getValue();
    }

    public Integer getMinMilkContainer() {
        return coffeeMachineConstantValueRepository.findByName("min_milk_container").getValue();
    }

    public Integer getMaxMilkContainer() {
        return coffeeMachineConstantValueRepository.findByName("max_milk_container").getValue();
    }

    public Integer getMinCoffeeBeansContainer() {
        return coffeeMachineConstantValueRepository.findByName("min_coffee_beans_container").getValue();
    }

    public Integer getMaxCoffeeBeansContainer() {
        return coffeeMachineConstantValueRepository.findByName("max_coffee_beans_container").getValue();
    }

    public Integer getMaxGroundContainer() {
        return coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
    }

    public Integer getMaxDescaleCounter() {
        return coffeeMachineConstantValueRepository.findByName("max_descale_counter").getValue();
    }

    public Integer getMaxGrindingLevel() {
        return coffeeMachineConstantValueRepository.findByName("max_grinding_level").getValue();
    }

    public Integer getMinGrindingLevel() {
        return coffeeMachineConstantValueRepository.findByName("min_grinding_level").getValue();
    }

    public Integer getMaxAmountOfCoffee() {
        return coffeeMachineConstantValueRepository.findByName("max_amount_of_coffee").getValue();
    }

    public Integer getMinAmountOfCoffee() {
        return coffeeMachineConstantValueRepository.findByName("min_amount_of_coffee").getValue();
    }

    public Integer getMaxTempWater() {
        return coffeeMachineConstantValueRepository.findByName("max_temp_water").getValue();
    }

    public Integer getMinTempWater() {
        return coffeeMachineConstantValueRepository.findByName("min_temp_water").getValue();
    }

    public Integer getMaxAmountOfWater() {
        return coffeeMachineConstantValueRepository.findByName("max_amount_of_water").getValue();
    }

    public Integer getMinAmountOfWater() {
        return coffeeMachineConstantValueRepository.findByName("min_amount_of_water").getValue();
    }

    public Integer getMaxTempMilk() {
        return coffeeMachineConstantValueRepository.findByName("max_temp_milk").getValue();
    }

    public Integer getMinTempMilk() {
        return coffeeMachineConstantValueRepository.findByName("min_temp_milk").getValue();
    }

    public Integer getMaxAmountOfMilk() {
        return coffeeMachineConstantValueRepository.findByName("max_amount_of_milk").getValue();
    }

    public Integer getMinAmountOfMilk() {
        return coffeeMachineConstantValueRepository.findByName("min_amount_of_milk").getValue();
    }

    public Integer getMaxCupSize() {
        return coffeeMachineConstantValueRepository.findByName("max_cup_size").getValue();
    }

    public Integer getMinCupSize() {
        return coffeeMachineConstantValueRepository.findByName("min_cup_size").getValue();
    }

    public Integer getWarningLevelWater() {
        return coffeeMachineConstantValueRepository.findByName("warning_level_water").getValue();
    }

    public Integer getDangerLevelWater() {
        return coffeeMachineConstantValueRepository.findByName("danger_level_water").getValue();
    }

    public Integer getWarningLevelMilk() {
        return coffeeMachineConstantValueRepository.findByName("warning_level_milk").getValue();
    }

    public Integer getDangerLevelMilk() {
        return coffeeMachineConstantValueRepository.findByName("danger_level_milk").getValue();
    }

    public Integer getWarningLevelCoffeeBeans() {
        return coffeeMachineConstantValueRepository.findByName("warning_level_coffee_beans").getValue();
    }

    public Integer getDangerLevelCoffeeBeans() {
        return coffeeMachineConstantValueRepository.findByName("danger_level_coffee_beans").getValue();
    }

    public Integer getWarningLevelGroundContainer() {
        Integer value = coffeeMachineConstantValueRepository.findByName("warning_level_ground_container").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
        return baseValue - value;
    }

    public Integer getDangerLevelGroundContainer() {
        Integer value = coffeeMachineConstantValueRepository.findByName("danger_level_ground_container").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
        return baseValue - value;
    }

    public Integer getWarningLevelDescale() {
        Integer value = coffeeMachineConstantValueRepository.findByName("warning_level_descale").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_descale_counter").getValue();
        return baseValue - value;
    }

    public Integer getDangerLevelDescale() {
        Integer value = coffeeMachineConstantValueRepository.findByName("danger_level_descale").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
        return baseValue - value;
    }

    public void updateConstantValue(CoffeeMachineConstantValueDto machineConstDto) {
        CoffeeMachineConstantValue machineConst = coffeeMachineConstantValueRepository.findByName(machineConstDto.getName());
        machineConst.setValue(machineConstDto.getValue());
        coffeeMachineConstantValueRepository.save(machineConst);
    }
}