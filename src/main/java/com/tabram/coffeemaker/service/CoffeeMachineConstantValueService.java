package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.CoffeeMachineConstantValueDto;
import com.tabram.coffeemaker.model.CoffeeMachineConstantValue;
import com.tabram.coffeemaker.repository.CoffeeMachineConstantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.tabram.coffeemaker.service.CoffeeMachineConstantValueNames.*;

@Service
public class CoffeeMachineConstantValueService {


    private static final String MAX_GROUND_CONTAINER = "max_ground_container";
    private final CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository;

    @Autowired
    public CoffeeMachineConstantValueService(CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository) {
        this.coffeeMachineConstantValueRepository = coffeeMachineConstantValueRepository;
    }

    private void setValue(String name, Integer value) {
        CoffeeMachineConstantValue machineConst = coffeeMachineConstantValueRepository.findByName(name);
        machineConst.setValue(value);
        coffeeMachineConstantValueRepository.save(machineConst);
    }

    private Integer getValue(String name) {
        return coffeeMachineConstantValueRepository.findByName(name).getValue();
    }

    public Integer getMinWaterContainer() {
        return getValue(MIN_WATER_CONTAINER);
    }

    public void setMinWaterContainer(Integer value) {
        setValue(MIN_WATER_CONTAINER, value);
    }

    public Integer getMaxWaterContainer() {
        return getValue(MAX_WATER_CONTAINER);
    }

    public void setMaxWaterContainer(Integer value) {
        setValue(MAX_WATER_CONTAINER, value);
    }

    public Integer getMinMilkContainer() {
        return getValue(MIN_MILK_CONTAINER);
    }

    public void setMinMilkContainer(Integer value) {
        setValue(MIN_MILK_CONTAINER, value);
    }

    public Integer getMaxMilkContainer() {
        return getValue(MAX_MILK_CONTAINER);
    }

    public void setMaxMilkContainer(Integer value) {
        setValue(MAX_MILK_CONTAINER, value);
    }

    public Integer getMinCoffeeBeansContainer() {
        return getValue(MIN_COFFEE_BEANS_CONTAINER);
    }

    public void setMinCoffeeBeansContainer(Integer value) {
        setValue(MIN_COFFEE_BEANS_CONTAINER, value);
    }

    public Integer getMaxCoffeeBeansContainer() {
        return getValue(MAX_COFFEE_BEANS_CONTAINER);
    }

    public void setMaxCoffeeBeansContainer(Integer value) {
        setValue(MAX_COFFEE_BEANS_CONTAINER, value);
    }

    public Integer getMaxGroundContainer() {
        return getValue(MAX_GROUND_CONTAINER);
    }

    public void setMaxGroundContainer(Integer value) {
        setValue(MAX_GROUND_CONTAINER, value);
    }

    public Integer getMinGroundContainer() {
        return getValue(MIN_GROUND_CONTAINER);
    }

    public void setMinGroundContainer(Integer value) {
        setValue(MIN_GROUND_CONTAINER, value);
    }

    public Integer getMaxDescaleCounter() {
        return getValue(MAX_DESCALE_COUNTER);
    }

    public void setMaxDescaleCounter(Integer value) {
        setValue(MAX_DESCALE_COUNTER, value);
    }

    public Integer getMinDescaleCounter() {
        return getValue(MIN_DESCALE_COUNTER);
    }

    public void setMinDescaleCounter(Integer value) {
        setValue(MIN_DESCALE_COUNTER, value);
    }

    public Integer getMaxGrindingLevel() {
        return getValue(MAX_GRINDING_LEVEL);
    }

    public void setMaxGrindingLevel(Integer value) {
        setValue(MAX_GRINDING_LEVEL, value);
    }

    public Integer getMinGrindingLevel() {
        return getValue(MIN_GRINDING_LEVEL);
    }

    public void setMinGrindingLevel(Integer value) {
        setValue(MIN_GRINDING_LEVEL, value);
    }

    public Integer getMaxAmountOfCoffee() {
        return getValue(MAX_AMOUNT_OF_COFFEE);
    }

    public void setMaxAmountOfCoffee(Integer value) {
        setValue(MAX_AMOUNT_OF_COFFEE, value);
    }

    public Integer getMinAmountOfCoffee() {
        return getValue(MIN_AMOUNT_OF_COFFEE);
    }

    public void setMinAmountOfCoffee(Integer value) {
        setValue(MIN_AMOUNT_OF_COFFEE, value);
    }

    public Integer getMaxTempWater() {
        return getValue(MAX_TEMP_WATER);
    }

    public void setMaxTempWater(Integer value) {
        setValue(MAX_TEMP_WATER, value);
    }

    public Integer getMinTempWater() {
        return getValue(MIN_TEMP_WATER);
    }

    public void setMinTempWater(Integer value) {
        setValue(MIN_TEMP_WATER, value);
    }

    public Integer getMaxAmountOfWater() {
        return getValue(MAX_AMOUNT_OF_WATER);
    }

    public void setMaxAmountOfWater(Integer value) {
        setValue(MAX_AMOUNT_OF_WATER, value);
    }

    public Integer getMinAmountOfWater() {
        return getValue(MIN_AMOUNT_OF_WATER);
    }

    public void setMinAmountOfWater(Integer value) {
        setValue(MIN_AMOUNT_OF_WATER, value);
    }

    public Integer getMaxTempMilk() {
        return getValue(MAX_TEMP_MILK);
    }

    public void setMaxTempMilk(Integer value) {
        setValue(MAX_TEMP_MILK, value);
    }

    public Integer getMinTempMilk() {
        return getValue(MIN_TEMP_MILK);
    }

    public void setMinTempMilk(Integer value) {
        setValue(MIN_TEMP_MILK, value);
    }

    public Integer getMaxAmountOfMilk() {
        return getValue(MAX_AMOUNT_OF_MILK);
    }

    public void setMaxAmountOfMilk(Integer value) {
        setValue(MAX_AMOUNT_OF_MILK, value);
    }

    public Integer getMinAmountOfMilk() {
        return getValue(MIN_AMOUNT_OF_MILK);
    }

    public void setMinAmountOfMilk(Integer value) {
        setValue(MIN_AMOUNT_OF_MILK, value);
    }

    public Integer getMaxCupSize() {
        return getValue(MAX_CUP_SIZE);
    }

    public void setMaxCupSize(Integer value) {
        setValue(MAX_CUP_SIZE, value);
    }

    public Integer getMinCupSize() {
        return getValue(MIN_CUP_SIZE);
    }

    public void setMinCupSize(Integer value) {
        setValue(MIN_CUP_SIZE, value);
    }

    public Integer getWarningLevelWater() {
        return getValue(WARNING_LEVEL_WATER);
    }

    public void setWarningLevelWater(Integer value) {
        setValue(WARNING_LEVEL_WATER, value);
    }

    public Integer getDangerLevelWater() {
        return getValue(DANGER_LEVEL_WATER);
    }

    public void setDangerLevelWater(Integer value) {
        setValue(DANGER_LEVEL_WATER, value);
    }

    public Integer getWarningLevelMilk() {
        return getValue(WARNING_LEVEL_MILK);
    }

    public void setWarningLevelMilk(Integer value) {
        setValue(WARNING_LEVEL_MILK, value);
    }

    public Integer getDangerLevelMilk() {
        return getValue(DANGER_LEVEL_MILK);
    }

    public void setDangerLevelMilk(Integer value) {
        setValue(DANGER_LEVEL_MILK, value);
    }

    public Integer getWarningLevelCoffeeBeans() {
        return getValue(WARNING_LEVEL_COFFEE_BEANS);
    }

    public void setWarningLevelCoffeeBeans(Integer value) {
        setValue(WARNING_LEVEL_COFFEE_BEANS, value);
    }

    public Integer getDangerLevelCoffeeBeans() {
        return getValue(DANGER_LEVEL_COFFEE_BEANS);
    }

    public void setDangerLevelCoffeeBeans(Integer value) {
        setValue(DANGER_LEVEL_COFFEE_BEANS, value);
    }

    public Integer getWarningLevelGroundContainer() {
        Integer value = getValue(WARNING_LEVEL_GROUND_CONTAINER);
        Integer baseValue = getValue(MAX_GROUND_CONTAINER);
        return baseValue - value;
    }

    public Integer getDangerLevelGroundContainer() {
        Integer value = getValue(DANGER_LEVEL_GROUND_CONTAINER);
        Integer baseValue = getValue(MAX_GROUND_CONTAINER);
        return baseValue - value;
    }

    public Integer getWarningLevelDescale() {
        Integer value = getValue(WARNING_LEVEL_DESCALE);
        Integer baseValue = getValue(MAX_DESCALE_COUNTER);
        return baseValue - value;
    }

    public Integer getDangerLevelDescale() {
        Integer value = getValue(DANGER_LEVEL_DESCALE);
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