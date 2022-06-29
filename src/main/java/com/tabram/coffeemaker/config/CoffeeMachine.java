package com.tabram.coffeemaker.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CoffeeMachine {

    final long MACHINE_ID = 1L;
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
    final double MAX_AMOUNT_OF_COFFEE = 40;
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

    public long getMACHINE_ID() {
        return MACHINE_ID;
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


}
