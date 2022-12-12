package com.tabram.coffeemaker.dto;

import com.tabram.coffeemaker.validation.*;

import javax.validation.constraints.NotEmpty;


public class CoffeeDto {

    @NotEmpty(message = "{error.empty.coffeeName}")
    private String coffeeName;
    @TempWater
    private int tempWater;
    @GrindingLevel
    private int grindingLevel;
    @AmountOfCoffee
    private double amountOfCoffee;
    @AmountOfWater
    private int amountOfWater;
    @AmountOfMilk
    private int amountMilk;
    @TempMilk
    private int tempMilk;
    @CupSize
    private int cupSize;

    public CoffeeDto() {
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
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

    @Override
    public String toString() {
        return "CoffeeDto{" +
                "coffeeName='" + coffeeName + '\'' +
                ", tempWater=" + tempWater +
                ", grindingLevel=" + grindingLevel +
                ", amountOfCoffee=" + amountOfCoffee +
                ", amountOfWater=" + amountOfWater +
                ", amountMilk=" + amountMilk +
                ", tempMilk=" + tempMilk +
                ", cupSize=" + cupSize +
                '}';
    }
}
