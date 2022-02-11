package com.tabram.coffemaker;

import java.util.EnumMap;
import java.util.Map;

public class Status {
    private int waterLevel;
    private int milkLevel;
    private float coffeeBeansLevel;
    private int groundContainer;
    private int coffeeCounter;
    private float waterHardness;

    public Status() {
        this.waterLevel = 250;
        this.milkLevel = 150;
        this.coffeeBeansLevel = 200;
        this.groundContainer = 20;
        this.coffeeCounter = 15;
        this.waterHardness = 5;
    }


    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getMilkLevel() {
        return milkLevel;
    }

    public void setMilkLevel(int milkLevel) {
        this.milkLevel = milkLevel;
    }

    public float getCoffeeBeansLevel() {
        return coffeeBeansLevel;
    }

    public void setCoffeeBeansLevel(float coffeeBeansLevel) {
        this.coffeeBeansLevel = coffeeBeansLevel;
    }

    public int getGroundContainer() {
        return groundContainer;
    }

    public void setGroundContainer(int groundContainer) {
        this.groundContainer = groundContainer;
    }

    public int getCoffeeCounter() {
        return coffeeCounter;
    }

    public void setCoffeeCounter(int coffeeCounter) {
        this.coffeeCounter = coffeeCounter;
    }

    public float getWaterHardness() {
        return waterHardness;
    }

    public void setWaterHardness(float waterHardness) {
        this.waterHardness = waterHardness;
    }

    public float getScaleCounter() {
        return coffeeCounter * waterHardness;
    }


    public Map<StatusEnum, String> checkStatus() {
        Map<StatusEnum, String> statusMap = new EnumMap<>(StatusEnum.class);
        String waterStatus;
        String milkStatus;
        String coffeeBeansStatus;
        String groundContainerStatus;
        String descaleStatus;

        if (waterLevel < StatusEnum.WATER_LEVEL.getWarningLevel()) {
            waterStatus = "Not enough water! " + waterLevel + "ml in the container. [" + StatusEnum.WATER_LEVEL.getWarningLevel() + "ml min]";
            statusMap.put(StatusEnum.WATER_LEVEL, waterStatus);
        } else {
            waterStatus = "OK! " + waterLevel + "ml in the container. [" + StatusEnum.WATER_LEVEL.getWarningLevel() + "ml min]";
            statusMap.put(StatusEnum.WATER_LEVEL, waterStatus);
        }

        if (milkLevel < StatusEnum.MILK_LEVEL.getWarningLevel()) {
            milkStatus = "Not enough MILK! " + milkLevel + "ml in the container. [" + StatusEnum.MILK_LEVEL.getWarningLevel() + "ml min]";
            statusMap.put(StatusEnum.MILK_LEVEL, milkStatus);
        } else {
            milkStatus = "OK! " + milkLevel + "ml in the container. [" + StatusEnum.MILK_LEVEL.getWarningLevel() + "ml min]";
            statusMap.put(StatusEnum.MILK_LEVEL, milkStatus);
        }

        if (coffeeBeansLevel < StatusEnum.COFFEE_BEANS_LEVEL.getWarningLevel()) {
            coffeeBeansStatus = "Not enough coffee beans! " + coffeeBeansLevel + "g in the container. [" + StatusEnum.COFFEE_BEANS_LEVEL.getWarningLevel() + "g min]";
            statusMap.put(StatusEnum.COFFEE_BEANS_LEVEL, coffeeBeansStatus);
        } else {
            coffeeBeansStatus = "OK! " + coffeeBeansLevel + "g in the container. [" + StatusEnum.COFFEE_BEANS_LEVEL.getWarningLevel() + "g min].";
            statusMap.put(StatusEnum.COFFEE_BEANS_LEVEL, coffeeBeansStatus);
        }

        if (groundContainer > StatusEnum.GROUND_CONTAINER.getWarningLevel()) {
            groundContainerStatus = "Ground container is full [" + StatusEnum.GROUND_CONTAINER.getWarningLevel() + " pieces]";
            statusMap.put(StatusEnum.GROUND_CONTAINER, groundContainerStatus);
        } else {
            groundContainerStatus = "Relax you can still make " + (StatusEnum.GROUND_CONTAINER.getWarningLevel() - groundContainer) + " single coffees.";
            statusMap.put(StatusEnum.GROUND_CONTAINER, groundContainerStatus);
        }

        if (getScaleCounter() > StatusEnum.SCALE_COUNTER.getWarningLevel()) {
            descaleStatus = "If you want to enjoy delicious coffee all the time, DESCALE the coffee machine now! [" + StatusEnum.SCALE_COUNTER.getWarningLevel() + "]";
            statusMap.put(StatusEnum.SCALE_COUNTER, descaleStatus);
        } else {
            descaleStatus = "You can still make around " + ((StatusEnum.SCALE_COUNTER.getWarningLevel() - getScaleCounter()) / waterHardness) + " single coffees. [" + StatusEnum.SCALE_COUNTER.getWarningLevel() + " max level]";
            statusMap.put(StatusEnum.SCALE_COUNTER, descaleStatus);
        }
        statusMap.forEach((k, v) -> System.out.println(k + " " + v));
        return statusMap;
    }


}
