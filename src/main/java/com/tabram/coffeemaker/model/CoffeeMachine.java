package com.tabram.coffeemaker.model;


public class CoffeeMachine {


private final int MAX_GRINDING_LEVEL = 10;
private final int MIN_GRINDING_LEVEL = 1;

private final double MAX_AMOUNT_OF_COFFEE = 40;
private final double MIN_AMOUNT_OF_COFFEE = 10;

private final int   MAX_TEMP_WATER = 98;
private final int MIN_TEMP_WATER = 30;
private final int MAX_AMOUNT_OF_WATER = 500;
private final int MIN_AMOUNT_OF_WATER = 15;

private final int  MAX_TEMP_MILK = 98;
private final int MIN_TEMP_MILK = 1;
private final int MAX_AMOUNT_OF_MILK = 500;
private final int MIN_AMOUNT_OF_MILK = 0;


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

    private final int MAX_CUP_SIZE = 1000;
private final int MIN_CUP_SIZE = 15;

}
