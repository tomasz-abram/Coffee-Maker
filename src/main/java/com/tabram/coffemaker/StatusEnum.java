package com.tabram.coffemaker;

public enum StatusEnum {
    WATER_LEVEL(150),
    MILK_LEVEL(150),
    COFFEE_BEANS_LEVEL(40),
    GROUND_CONTAINER(25),
    SCALE_COUNTER(250);


    private int warningLevel;
    StatusEnum(int warningLevel) {
        this.warningLevel = warningLevel;
    }

    public int getWarningLevel() {
        return warningLevel;
    }

}
