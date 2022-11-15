package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeMachineConstantValueDto;
import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import com.tabram.coffeemaker.repository.CoffeeMachineConstantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CoffeeMachineConstantValueService {


    private final CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository;
    private static final String MAX_GROUND_CONTAINER = "max_ground_container";

    @Autowired
    public CoffeeMachineConstantValueService(CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository) {
        this.coffeeMachineConstantValueRepository = coffeeMachineConstantValueRepository;
    }

    public Integer getValue(String name) {
        return coffeeMachineConstantValueRepository.findByName(name).getValue();
    }

    public Integer getMinWaterContainer() {
        return getValue("min_water_container");
    }

    public Integer getMaxWaterContainer() {
        return getValue("max_water_container");
    }

    public Integer getMinMilkContainer() {
        return getValue("min_milk_container");
    }

    public Integer getMaxMilkContainer() {
        return getValue("max_milk_container");
    }

    public Integer getMinCoffeeBeansContainer() {
        return getValue("min_coffee_beans_container");
    }

    public Integer getMaxCoffeeBeansContainer() {
        return getValue("max_coffee_beans_container");
    }

    public Integer getMaxGroundContainer() {
        return getValue(MAX_GROUND_CONTAINER);
    }

    public Integer getMinGroundContainer() {
        return getValue("min_ground_container");
    }

    public Integer getMaxDescaleCounter() {
        return getValue("max_descale_counter");
    }
    public Integer getMinDescaleCounter() {
        return getValue("min_descale_counter");
    }

    public Integer getMaxGrindingLevel() {
        return getValue("max_grinding_level");
    }

    public Integer getMinGrindingLevel() {
        return getValue("min_grinding_level");
    }

    public Integer getMaxAmountOfCoffee() {
        return getValue("max_amount_of_coffee");
    }

    public Integer getMinAmountOfCoffee() {
        return getValue("min_amount_of_coffee");
    }

    public Integer getMaxTempWater() {
        return getValue("max_temp_water");
    }

    public Integer getMinTempWater() {
        return getValue("min_temp_water");
    }

    public Integer getMaxAmountOfWater() {
        return getValue("max_amount_of_water");
    }

    public Integer getMinAmountOfWater() {
        return getValue("min_amount_of_water");
    }

    public Integer getMaxTempMilk() {
        return getValue("max_temp_milk");
    }

    public Integer getMinTempMilk() {
        return getValue("min_temp_milk");
    }

    public Integer getMaxAmountOfMilk() {
        return getValue("max_amount_of_milk");
    }

    public Integer getMinAmountOfMilk() {
        return getValue("min_amount_of_milk");
    }

    public Integer getMaxCupSize() {
        return getValue("max_cup_size");
    }

    public Integer getMinCupSize() {
        return getValue("min_cup_size");
    }

    public Integer getWarningLevelWater() {
        return getValue("warning_level_water");
    }

    public Integer getDangerLevelWater() {
        return getValue("danger_level_water");
    }

    public Integer getWarningLevelMilk() {
        return getValue("warning_level_milk");
    }

    public Integer getDangerLevelMilk() {
        return getValue("danger_level_milk");
    }

    public Integer getWarningLevelCoffeeBeans() {
        return getValue("warning_level_coffee_beans");
    }

    public Integer getDangerLevelCoffeeBeans() {
        return getValue("danger_level_coffee_beans");
    }

    public Integer getWarningLevelGroundContainer() {
        Integer value = getValue("warning_level_ground_container");
        Integer baseValue = getValue(MAX_GROUND_CONTAINER);
        return baseValue - value;
    }

    public Integer getDangerLevelGroundContainer() {
        Integer value = getValue("danger_level_ground_container");
        Integer baseValue = getValue(MAX_GROUND_CONTAINER);
        return baseValue - value;
    }

    public Integer getWarningLevelDescale() {
        Integer value = getValue("warning_level_descale");
        Integer baseValue = getValue("max_descale_counter");
        return baseValue - value;
    }

    public Integer getDangerLevelDescale() {
        Integer value = getValue("danger_level_descale");
        Integer baseValue = getValue(MAX_GROUND_CONTAINER);
        return baseValue - value;
    }

    public void updateConstantValue(CoffeeMachineConstantValueDto machineConstDto) {
        CoffeeMachineConstantValue machineConst = coffeeMachineConstantValueRepository.findByName(machineConstDto.getName());
        machineConst.setValue(machineConstDto.getValue());
        coffeeMachineConstantValueRepository.save(machineConst);
    }

    public List<CoffeeMachineConstantValue> getAllConstantValue() {
        return coffeeMachineConstantValueRepository.findAll();
    }

    public CoffeeMachineConstantValue findConstantValueById(Long constId) {
        return coffeeMachineConstantValueRepository.findById(constId)
                .orElseThrow(() -> new EntityNotFoundException("Constant value not found"));
    }
}