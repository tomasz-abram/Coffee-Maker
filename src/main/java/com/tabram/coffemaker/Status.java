package com.tabram.coffemaker;

import java.util.EnumMap;
import java.util.Map;

public class Status {
    public static int waterLevel = 250;
    public static final int MIN_WATER_LEVEL = 150;
    public static int milkLevel = 200;
    public static final int MIN_MILK_LEVEL = 150;
    public static float coffeeBeansLevel = 250;
    public static final float MIN_COFFEE_BEANS_LEVEL = 40;
    public static int groundContainer = 15;
    public static final int MAX_GROUND_CONTAINER = 25;
    public static float coffeeCounter = 23;
    public static float waterHardness = 5;
    public static float scaleCounter = coffeeCounter * waterHardness;
    public static final float MAX_SCALE_COUNTER = 250;


    private enum StatuEnums {
        WATER_LEVEL,
        MILK_LEVEL,
        COFFEE_BEANS_LEVEL,
        GROUND_CONTAINER,
        DESCALE_COUNTER
    }

    public Map<StatuEnums, String> checkStatus() {
        Map<StatuEnums, String> statusMap = new EnumMap<>(StatuEnums.class);
        String waterStatus;
        String milkStatus;
        String coffeeBeansStatus;
        String groundContainerStatus;
        String descaleStatus;

        if (waterLevel < MIN_WATER_LEVEL) {
            waterStatus = "Not enough water! " + waterLevel + "ml in the container. [" + MIN_WATER_LEVEL + "ml min]";
            statusMap.put(StatuEnums.WATER_LEVEL, waterStatus);
        } else {
            waterStatus = "OK! " + waterLevel + "ml in the container. [" + MIN_WATER_LEVEL + "ml min]";
            statusMap.put(StatuEnums.WATER_LEVEL, waterStatus);
        }

        if (milkLevel < MIN_MILK_LEVEL) {
            milkStatus = "Not enough MILK! " + milkLevel + "ml in the container. [" + MIN_MILK_LEVEL + "ml min]";
            statusMap.put(StatuEnums.MILK_LEVEL, milkStatus);
        } else {
            milkStatus = "OK! " + milkLevel + "ml in the container. [" + MIN_MILK_LEVEL + "ml min]";
            statusMap.put(StatuEnums.MILK_LEVEL, milkStatus);
        }

        if (coffeeBeansLevel < MIN_COFFEE_BEANS_LEVEL) {
            coffeeBeansStatus = "Not enough coffee beans! " + coffeeBeansLevel + "g in the container. [" + MIN_COFFEE_BEANS_LEVEL + "g min]";
            statusMap.put(StatuEnums.COFFEE_BEANS_LEVEL, coffeeBeansStatus);
        } else {
            coffeeBeansStatus = "OK! " + coffeeBeansLevel + "g in the container. [" + MIN_COFFEE_BEANS_LEVEL + "g min].";
            statusMap.put(StatuEnums.COFFEE_BEANS_LEVEL, coffeeBeansStatus);
        }

        if (groundContainer > MAX_GROUND_CONTAINER) {
            groundContainerStatus = "Ground container is full [" + MAX_GROUND_CONTAINER + " pieces]";
            statusMap.put(StatuEnums.GROUND_CONTAINER, groundContainerStatus);
        } else {
            groundContainerStatus = "Relax you can still make " + (MAX_GROUND_CONTAINER - groundContainer) + " single coffees.";
            statusMap.put(StatuEnums.GROUND_CONTAINER, groundContainerStatus);
        }

        if (scaleCounter > MAX_SCALE_COUNTER) {
            descaleStatus = "If you want to enjoy delicious coffee all the time, DESCALE the coffee machine now! [" + MAX_SCALE_COUNTER + "]";
            statusMap.put(StatuEnums.DESCALE_COUNTER, descaleStatus);
        } else {
            descaleStatus = "You can still make around " + ((MAX_SCALE_COUNTER - scaleCounter) / waterHardness) + " single coffees. [" + MAX_SCALE_COUNTER + " max level]";
            statusMap.put(StatuEnums.DESCALE_COUNTER, descaleStatus);
        }
        System.out.print(statusMap);
        return statusMap;
    }




}
