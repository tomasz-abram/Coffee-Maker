package com.tabram.coffeemaker.config;

import org.springframework.stereotype.Component;

@Component
public class CoffeeMachine {

    //Defining the maximum and minimum values for the coffee machine
    final int MIN_WATER_CONTAINER = 0;
    final int MAX_WATER_CONTAINER = 3000;
    final int MIN_MILK_CONTAINER = 0;
    final int MAX_MILK_CONTAINER = 1500;
    final int MIN_COFFEE_BEANS_CONTAINER = 0;
    final int MAX_COFFEE_BEANS_CONTAINER = 300;
    final int MIN_GROUND_CONTAINER = 0;
    final int MAX_GROUND_CONTAINER = 30;
    final int MAX_DESCALE_COUNTER = 50000;
    final int MAX_GRINDING_LEVEL = 10;
    final int MIN_GRINDING_LEVEL = 1;
    final double MAX_AMOUNT_OF_COFFEE = 30;
    final double MIN_AMOUNT_OF_COFFEE = 10;
    final int MAX_TEMP_WATER = 98;
    final int MIN_TEMP_WATER = 30;
    final int MAX_AMOUNT_OF_WATER = 500;
    final int MIN_AMOUNT_OF_WATER = 15;
    final int MAX_TEMP_MILK = 90;
    final int MIN_TEMP_MILK = 1;
    final int MAX_AMOUNT_OF_MILK = 500;
    final int MIN_AMOUNT_OF_MILK = 0;
    final int MAX_CUP_SIZE = 1000;
    final int MIN_CUP_SIZE = 15;

    // Component alarm values
    final int WARNING_LEVEL_WATER = 500;
    final int DANGER_LEVEL_WATER = 200;
    final int WARNING_LEVEL_MILK = 500;
    final int DANGER_LEVEL_MILK = 200;
    final int WARNING_LEVEL_COFFEE_BEANS = 90;
    final int DANGER_LEVEL_COFFEE_BEANS = 20;
    final int WARNING_LEVEL_GROUND_CONTAINER = MAX_GROUND_CONTAINER - 3;
    final int DANGER_LEVEL_GROUND_CONTAINER = MAX_GROUND_CONTAINER - 1;
    final int WARNING_LEVEL_DESCALE = MAX_DESCALE_COUNTER - 5000;
    final int DANGER_LEVEL_DESCALE = MAX_DESCALE_COUNTER - 2000;

    public int getWARNING_LEVEL_GROUND_CONTAINER() {
        return WARNING_LEVEL_GROUND_CONTAINER;
    }

    public int getDANGER_LEVEL_GROUND_CONTAINER() {
        return DANGER_LEVEL_GROUND_CONTAINER;
    }

    public int getMIN_WATER_CONTAINER() {
        return MIN_WATER_CONTAINER;
    }

    public int getMAX_WATER_CONTAINER() {
        return MAX_WATER_CONTAINER;
    }

    public int getMIN_MILK_CONTAINER() {
        return MIN_MILK_CONTAINER;
    }

    public int getMAX_MILK_CONTAINER() {
        return MAX_MILK_CONTAINER;
    }

    public int getMIN_COFFEE_BEANS_CONTAINER() {
        return MIN_COFFEE_BEANS_CONTAINER;
    }

    public int getMAX_COFFEE_BEANS_CONTAINER() {
        return MAX_COFFEE_BEANS_CONTAINER;
    }

    public int getMIN_GROUND_CONTAINER() {
        return MIN_GROUND_CONTAINER;
    }

    public int getMAX_GROUND_CONTAINER() {
        return MAX_GROUND_CONTAINER;
    }

    public int getMAX_DESCALE_COUNTER() {
        return MAX_DESCALE_COUNTER;
    }

    public int getMAX_GRINDING_LEVEL() {
        return MAX_GRINDING_LEVEL;
    }

    public int getMIN_GRINDING_LEVEL() {
        return MIN_GRINDING_LEVEL;
    }

    public double getMAX_AMOUNT_OF_COFFEE() {
        return MAX_AMOUNT_OF_COFFEE;
    }

    public double getMIN_AMOUNT_OF_COFFEE() {
        return MIN_AMOUNT_OF_COFFEE;
    }

    public int getMAX_TEMP_WATER() {
        return MAX_TEMP_WATER;
    }

    public int getMIN_TEMP_WATER() {
        return MIN_TEMP_WATER;
    }

    public int getMAX_AMOUNT_OF_WATER() {
        return MAX_AMOUNT_OF_WATER;
    }

    public int getMIN_AMOUNT_OF_WATER() {
        return MIN_AMOUNT_OF_WATER;
    }

    public int getMAX_TEMP_MILK() {
        return MAX_TEMP_MILK;
    }

    public int getMIN_TEMP_MILK() {
        return MIN_TEMP_MILK;
    }

    public int getMAX_AMOUNT_OF_MILK() {
        return MAX_AMOUNT_OF_MILK;
    }

    public int getMIN_AMOUNT_OF_MILK() {
        return MIN_AMOUNT_OF_MILK;
    }

    public int getMAX_CUP_SIZE() {
        return MAX_CUP_SIZE;
    }

    public int getMIN_CUP_SIZE() {
        return MIN_CUP_SIZE;
    }

    public int getWARNING_LEVEL_WATER() {
        return WARNING_LEVEL_WATER;
    }

    public int getDANGER_LEVEL_WATER() {
        return DANGER_LEVEL_WATER;
    }

    public int getWARNING_LEVEL_MILK() {
        return WARNING_LEVEL_MILK;
    }

    public int getDANGER_LEVEL_MILK() {
        return DANGER_LEVEL_MILK;
    }

    public int getWARNING_LEVEL_COFFEE_BEANS() {
        return WARNING_LEVEL_COFFEE_BEANS;
    }

    public int getDANGER_LEVEL_COFFEE_BEANS() {
        return DANGER_LEVEL_COFFEE_BEANS;
    }

    public int getWARNING_LEVEL_DESCALE() {
        return WARNING_LEVEL_DESCALE;
    }

    public int getDANGER_LEVEL_DESCALE() {
        return DANGER_LEVEL_DESCALE;
    }


}