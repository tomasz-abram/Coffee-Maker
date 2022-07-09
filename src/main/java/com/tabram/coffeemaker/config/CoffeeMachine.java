package com.tabram.coffeemaker.config;

import com.tabram.coffeemaker.repository.CoffeeMachineConstantValueRepository;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachine {

    private final CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository;

    public CoffeeMachine(CoffeeMachineConstantValueRepository coffeeMachineConstantValueRepository) {
        this.coffeeMachineConstantValueRepository = coffeeMachineConstantValueRepository;
    }

    public Integer getMIN_WATER_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("min_water_container").getValue();
    }

    public void setMIN_WATER_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_water_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_WATER_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("max_water_container").getValue();
    }

    public void setMAX_WATER_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_water_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_MILK_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("min_milk_container").getValue();
    }

    public void setMIN_MILK_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_milk_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_MILK_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("max_milk_container").getValue();
    }

    public void setMAX_MILK_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_milk_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_COFFEE_BEANS_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("min_coffee_beans_container").getValue();
    }

    public void setMIN_COFFEE_BEANS_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_coffee_beans_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_COFFEE_BEANS_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("max_coffee_beans_container").getValue();
    }

    public void setMAX_COFFEE_BEANS_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_coffee_beans_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_GROUND_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("min_ground_container").getValue();
    }

    public void setMIN_GROUND_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_ground_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_GROUND_CONTAINER() {
        return coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
    }

    public void setMAX_GROUND_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_ground_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_DESCALE_COUNTER() {
        return coffeeMachineConstantValueRepository.findByName("max_descale_counter").getValue();
    }

    public void setMAX_DESCALE_COUNTER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_descale_counter");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_GRINDING_LEVEL() {
        return coffeeMachineConstantValueRepository.findByName("max_grinding_level").getValue();
    }

    public void setMAX_GRINDING_LEVEL(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_grinding_level");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_GRINDING_LEVEL() {
        return coffeeMachineConstantValueRepository.findByName("min_grinding_level").getValue();
    }

    public void setMIN_GRINDING_LEVEL(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_grinding_level");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_AMOUNT_OF_COFFEE() {
        return coffeeMachineConstantValueRepository.findByName("max_amount_of_coffee").getValue();
    }

    public void setMAX_AMOUNT_OF_COFFEE(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_amount_of_coffee");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_AMOUNT_OF_COFFEE() {
        return coffeeMachineConstantValueRepository.findByName("min_amount_of_coffee").getValue();
    }

    public void setMIN_AMOUNT_OF_COFFEE(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_amount_of_coffee");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_TEMP_WATER() {
        return coffeeMachineConstantValueRepository.findByName("max_temp_water").getValue();
    }

    public void setMAX_TEMP_WATER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_temp_water");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_TEMP_WATER() {
        return coffeeMachineConstantValueRepository.findByName("min_temp_water").getValue();
    }

    public void setMIN_TEMP_WATER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_temp_water");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_AMOUNT_OF_WATER() {
        return coffeeMachineConstantValueRepository.findByName("max_amount_of_water").getValue();
    }

    public void setMAX_AMOUNT_OF_WATER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_amount_of_water");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_AMOUNT_OF_WATER() {
        return coffeeMachineConstantValueRepository.findByName("min_amount_of_water").getValue();
    }

    public void setMIN_AMOUNT_OF_WATER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_amount_of_water");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_TEMP_MILK() {
        return coffeeMachineConstantValueRepository.findByName("max_temp_milk").getValue();
    }

    public void setMAX_TEMP_MILK(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_temp_milk");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_TEMP_MILK() {
        return coffeeMachineConstantValueRepository.findByName("min_temp_milk").getValue();
    }

    public void setMIN_TEMP_MILK(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_temp_milk");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_AMOUNT_OF_MILK() {
        return coffeeMachineConstantValueRepository.findByName("max_amount_of_milk").getValue();
    }

    public void setMAX_AMOUNT_OF_MILK(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_amount_of_milk");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_AMOUNT_OF_MILK() {
        return coffeeMachineConstantValueRepository.findByName("min_amount_of_milk").getValue();
    }

    public void setMIN_AMOUNT_OF_MILK(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_amount_of_milk");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMAX_CUP_SIZE() {
        return coffeeMachineConstantValueRepository.findByName("max_cup_size").getValue();
    }

    public void setMAX_CUP_SIZE(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("max_cup_size");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getMIN_CUP_SIZE() {
        return coffeeMachineConstantValueRepository.findByName("min_cup_size").getValue();
    }

    public void setMIN_CUP_SIZE(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("min_cup_size");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getWARNING_LEVEL_WATER() {
        return coffeeMachineConstantValueRepository.findByName("warning_level_water").getValue();
    }

    public void setWARNING_LEVEL_WATER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("warning_level_water");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getDANGER_LEVEL_WATER() {
        return coffeeMachineConstantValueRepository.findByName("danger_level_water").getValue();
    }

    public void setDANGER_LEVEL_WATER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("danger_level_water");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getWARNING_LEVEL_MILK() {
        return coffeeMachineConstantValueRepository.findByName("warning_level_milk").getValue();
    }

    public void setWARNING_LEVEL_MILK(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("warning_level_milk");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getDANGER_LEVEL_MILK() {
        return coffeeMachineConstantValueRepository.findByName("danger_level_milk").getValue();
    }

    public void setDANGER_LEVEL_MILK(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("danger_level_milk");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getWARNING_LEVEL_COFFEE_BEANS() {
        return coffeeMachineConstantValueRepository.findByName("warning_level_coffee_beans").getValue();
    }

    public void setWARNING_LEVEL_COFFEE_BEANS(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("warning_level_coffee_beans");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getDANGER_LEVEL_COFFEE_BEANS() {
        return coffeeMachineConstantValueRepository.findByName("danger_level_coffee_beans").getValue();
    }

    public void setDANGER_LEVEL_COFFEE_BEANS(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("danger_level_coffee_beans");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getWARNING_LEVEL_GROUND_CONTAINER() {
        Integer value = coffeeMachineConstantValueRepository.findByName("warning_level_ground_container").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
        return baseValue - value;
    }

    public void setWARNING_LEVEL_GROUND_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("warning_level_ground_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getDANGER_LEVEL_GROUND_CONTAINER() {
        Integer value = coffeeMachineConstantValueRepository.findByName("danger_level_ground_container").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
        return baseValue - value;
    }

    public void setDANGER_LEVEL_GROUND_CONTAINER(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("danger_level_ground_container");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getWARNING_LEVEL_DESCALE() {
        Integer value = coffeeMachineConstantValueRepository.findByName("warning_level_descale").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_descale_counter").getValue();
        return baseValue - value;
    }

    public void setWARNING_LEVEL_DESCALE(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("warning_level_descale");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }

    public Integer getDANGER_LEVEL_DESCALE() {
        Integer value = coffeeMachineConstantValueRepository.findByName("danger_level_descale").getValue();
        Integer baseValue = coffeeMachineConstantValueRepository.findByName("max_ground_container").getValue();
        return baseValue - value;
    }

    public void setDANGER_LEVEL_DESCALE(Integer value) {
        CoffeeMachineConstantValues object = coffeeMachineConstantValueRepository.findByName("danger_level_descale");
        object.setValue(value);
        coffeeMachineConstantValueRepository.save(object);
    }
}