package com.tabram.coffeemaker.dto;

public class CoffeeDto {

    private String nameOfCoffee;
    private int tempWater;
    private int grindingLevel;
    private double amountOfCoffee;
    private int amountOfWater;
    private int amountMilk;
    private int tempMilk;
    private int cupSize;

    public CoffeeDto() {
    }

    public CoffeeDto(String nameOfCoffee, int tempWater, int grindingLevel, double amountOfCoffee, int amountOfWater, int amountMilk, int tempMilk, int cupSize) {
        this.nameOfCoffee = nameOfCoffee;
        this.tempWater = tempWater;
        this.grindingLevel = grindingLevel;
        this.amountOfCoffee = amountOfCoffee;
        this.amountOfWater = amountOfWater;
        this.amountMilk = amountMilk;
        this.tempMilk = tempMilk;
        this.cupSize = cupSize;
    }

    public String getNameOfCoffee() {
        return nameOfCoffee;
    }

    public void setNameOfCoffee(String nameOfCoffee) {
        this.nameOfCoffee = nameOfCoffee;
    }

    public int getTempWater() {
        return tempWater;
    }

    public void setTempWater(int tempWater) {
        this.tempWater = tempWater;
    }

    public int getGrindingLevel() {
        return grindingLevel;
    }

    public void setGrindingLevel(int grindingLevel) {
        this.grindingLevel = grindingLevel;
    }

    public double getAmountOfCoffee() {
        return amountOfCoffee;
    }

    public void setAmountOfCoffee(double amountOfCoffee) {
        this.amountOfCoffee = amountOfCoffee;
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public int getAmountMilk() {
        return amountMilk;
    }

    public void setAmountMilk(int amountMilk) {
        this.amountMilk = amountMilk;
    }

    public int getCupSize() {
        return cupSize;
    }

    public void setCupSize(int cupSize) {
        this.cupSize = cupSize;
    }

    public int getTempMilk() {
        return tempMilk;
    }

    public void setTempMilk(int tempMilk) {
        this.tempMilk = tempMilk;
    }
}
