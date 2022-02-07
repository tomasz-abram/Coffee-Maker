package com.tabram.coffemaker;

import java.util.List;

public class Coffees {
    private final int tempWater;
    private final double pressure;
    private final int grindingLevel;
    private final double amountOfCoffee;
    private final int amountOfWater;
    private final int amountMilk;
    private final double amountSugar;
    private final int cupSize;

    public Coffees(int tempWater, double pressure, int grindingLevel, double amountOfCoffee, int amountOfWater, int amountMilk, double amountSugar, int cupSize) {
        this.tempWater = tempWater;
        this.pressure = pressure;
        this.grindingLevel = grindingLevel;
        this.amountOfCoffee = amountOfCoffee;
        this.amountOfWater = amountOfWater;
        this.amountMilk = amountMilk;
        this.amountSugar = amountSugar;
        this.cupSize = cupSize;
    }

}
