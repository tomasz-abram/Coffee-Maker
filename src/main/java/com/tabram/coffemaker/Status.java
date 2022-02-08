package com.tabram.coffemaker;

import java.util.EnumMap;
import java.util.Map;

public class Status {
    public static int WATER_LEVEL = 250;
    public static int MIN_WATER_LEVEL = 150;
    public static int MILK_LEVEL = 200;
    public static int MIN_MILK_LEVEL = 150;
    public static float COFFEE_BEANS_LEVEL = 250;
    public static float MIN_COFFEE_BEANS_LEVEL = 40;
    public static int GROUND_CONTAINER = 15;
    public static int MAX_GROUND_CONTAINER = 25;
    public static float COFFEE_COUNTER = 23;
    public static float MAX_DESCALE_COUNTER = 250;
    public static float WATER_HARDNESS = 5;
    public static float DESCALE_COUNTER = COFFEE_COUNTER * WATER_HARDNESS;

    private enum StatuEnums {
        WATER,
        MILK,
        COFFEE_BEANS,
        GROUND_CONTAINER,
        DESCALE_COUNTER;
    }

    public Map<StatuEnums, String> checkStatus() {

        Map<StatuEnums,String> statusMap = new EnumMap<StatuEnums, String>();
        String waterStatus;
        String milkStatus;
        String coffeeBeansStatus;
        String groundContainerStatus;
        String descaleStatus;

        if (WATER_LEVEL < MIN_WATER_LEVEL) {
            waterStatus = "WATER LEVEL: Not enough water! " + WATER_LEVEL + "ml in the container. [" + MIN_WATER_LEVEL + "ml min]";
            statusMap.put(StatuEnums.WATER, waterStatus);
        } else {
            waterStatus="WATER LEVEL: OK! " + WATER_LEVEL + "ml in the container. [" + MIN_WATER_LEVEL + "ml min]";
            statusMap.put(StatuEnums.WATER, waterStatus);
        }

        if (MILK_LEVEL < MIN_MILK_LEVEL) {
            milkStatus = "MILK LEVEL: Not enough MILK! " + MILK_LEVEL + "ml in the container. [" + MIN_MILK_LEVEL + "ml min]";
            statusMap.put(StatuEnums.MILK, milkStatus);
        } else {
            milkStatus = "MILK LEVEL: OK! " + MILK_LEVEL + "ml in the container. [" + MIN_MILK_LEVEL + "ml min]";
            statusMap.put(StatuEnums.MILK, milkStatus);
        }

        if (COFFEE_BEANS_LEVEL < MIN_COFFEE_BEANS_LEVEL) {
            coffeeBeansStatus = "COFFEE BEANS LEVEL: Not enough coffee beans! " + COFFEE_BEANS_LEVEL + "g in the container. [" + MIN_COFFEE_BEANS_LEVEL + "g min]";
            statusMap.put(StatuEnums.COFFEE_BEANS, coffeeBeansStatus);
        } else {
            coffeeBeansStatus ="COFFEE BEANS LEVEL: OK! " + COFFEE_BEANS_LEVEL + "g in the container. [" + MIN_COFFEE_BEANS_LEVEL + "g min].";
            statusMap.put(StatuEnums.COFFEE_BEANS, coffeeBeansStatus);
        }

        if (GROUND_CONTAINER > MAX_GROUND_CONTAINER) {
            groundContainerStatus = "GROUND CONTAINER: Ground container is full [" + MAX_GROUND_CONTAINER + " pieces]";
            statusMap.put(StatuEnums.GROUND_CONTAINER, groundContainerStatus);
        } else {
            groundContainerStatus = "GROUND CONTAINER: Relax you can still make " + (MAX_GROUND_CONTAINER - GROUND_CONTAINER) + " single coffees.";
            statusMap.put(StatuEnums.GROUND_CONTAINER, groundContainerStatus);
        }

        if (DESCALE_COUNTER > MAX_DESCALE_COUNTER) {
            descaleStatus = "DESCALE COUNTER: If you want to enjoy delicious coffee all the time, DESCALE the coffee machine now! [" + MAX_DESCALE_COUNTER + "]";
            statusMap.put(StatuEnums.DESCALE_COUNTER, descaleStatus);
        } else {
            descaleStatus = "DESCALE COUNTER: You can still make around " + ((MAX_DESCALE_COUNTER - DESCALE_COUNTER) / WATER_HARDNESS) + " single coffees. [" + MAX_DESCALE_COUNTER + " max level]";
            statusMap.put(StatuEnums.DESCALE_COUNTER, descaleStatus);
        }

        return statusMap;
    }


}

//    public Map<String, String> checkStatus() {
//
//        StringBuilder builder = new StringBuilder();
//        Map<StatusEnums, String> statusMap = new EnumMap<StatusEnums, String>();
////        String waterStatus;
//
//        if (WATER_LEVEL < MIN_WATER_LEVEL) {
//            builder.append("WATER LEVEL: Not enough water! " + WATER_LEVEL + "ml in the container. [" + MIN_WATER_LEVEL + "ml min]");
////          waterStatus = "WATER LEVEL: Not enough water! \" + waterLevel + \"ml in the container. [\" + minWaterLevel + \"ml min]";
//            statusMap.put(StatusEnums.WATER, builder.toString());
//        } else {
//            builder.append("WATER LEVEL: OK! " + WATER_LEVEL + "ml in the container. [" + MIN_WATER_LEVEL + "ml min]");
////          waterStatus = "WATER LEVEL: OK! \" + waterLevel + \"ml in the container. [\" + minWaterLevel + \"ml min]";
//            statusMap.put(StatusEnums.WATER, builder.toString());
//        }
//
//        return statusMap<StatusEnums.WATER, builder.toString()>
//    }