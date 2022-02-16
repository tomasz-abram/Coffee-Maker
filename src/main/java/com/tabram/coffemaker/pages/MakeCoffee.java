package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;
import com.tabram.coffemaker.StatusEnum;


public class MakeCoffee {
    int quantity;
    Coffee coffee;
    boolean waterReady;
    boolean milkReady;
    boolean groundCoffeeReady;
    long t = System.currentTimeMillis();
    int preheatedWater = 65; // degree
    int accelerate = 10;
    CoffeeMaker coffeeMaker;

    public MakeCoffee(CoffeeMaker coffeeMaker, int quantity, Coffee coffee) {
        this.coffeeMaker = coffeeMaker;
        this.quantity = quantity;
        this.coffee = coffee;
        long waterHeatingTime = ((4180 * ((long) coffee.getAmountOfWater() * quantity) / 1000 * (coffee.getTempWater() - preheatedWater)) / 1800);
        long milkHeatingTime = ((4180 * ((long) coffee.getAmountMilk() * quantity) / 1000 * (70 - 20)) / 1800);
        long coffeeGrindingTime = (long) ((coffee.getAmountOfCoffee() * 17 / 20 - coffee.getGrindingLevel()) * quantity);
        checkQuantity();

        System.out.println("Start: Water heating...");
        System.out.println("Start: Grinding coffee...");
        if (coffee.getAmountMilk() > 0) {
            System.out.println("Start: Milk preparation heating and/or foaming.");
        }
        System.out.print("Processing...");


        while (!(waterReady && milkReady && groundCoffeeReady)) {
            long start = System.currentTimeMillis();

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print(".");


            if (start > waterHeatingTime * 1000 / accelerate + t && !waterReady) {
                System.out.println("\nWater is ready! " + (coffee.getAmountOfWater() * quantity) + "ml. after " + waterHeatingTime + "s from " + preheatedWater + "C\u00B0 heated to " + coffee.getTempWater() + "C\u00B0");
                waterReady = true;
            }
            if (coffee.getAmountMilk() > 0) {
                if (start > milkHeatingTime * 1000 / accelerate + t && !milkReady) {
                    milkReady = true;
                    System.out.println("\nMilk is ready! " + (coffee.getAmountMilk() * quantity) + "ml. after " + milkHeatingTime + "s heated to " + "70C\u00B0");
                }
            } else {
                milkReady = true;
            }
            if (start > coffeeGrindingTime * 1000 / accelerate + t && !groundCoffeeReady) {
                groundCoffeeReady = true;
                System.out.println("\nGround coffee is ready! " + (coffee.getAmountOfCoffee() * quantity) + "g. after " + coffeeGrindingTime + "s");
            }
        }
        subtractionOfIngredients();
        new FinalPage(coffeeMaker);

    }

    private void checkQuantity() {
       boolean goToStatusPage = false;
        if (coffeeMaker.status.getWaterLevel() < coffee.getAmountOfWater() * quantity) {
            System.err.println("Not enough water for coffees.");
            goToStatusPage = true;
        }
        if (coffeeMaker.status.getMilkLevel() < coffee.getAmountMilk() * quantity) {
            System.err.println("Not enough milk for coffees.");
            goToStatusPage = true;
        }
        if (coffeeMaker.status.getCoffeeBeansLevel() < coffee.getAmountOfCoffee() * quantity) {
            System.err.println("Not enough coffee beans for coffees.");
            goToStatusPage = true;
        }
        if (coffeeMaker.status.getGroundContainer() + quantity > StatusEnum.GROUND_CONTAINER.getWarningLevel()) {
            System.err.println("I cannot make coffees, because you will overfill the ground container.");
            System.out.println("Maybe you want a single coffee? If you don't, please, empty the ground container.");
            goToStatusPage = true;
        }
        if (goToStatusPage == true) {
            new StatusPage(coffeeMaker);
        }
        if ((coffee.getAmountOfWater() + coffee.getAmountMilk()) * quantity > coffee.getCupSize()) {
            System.err.println("Too small cup.");
            new ChangeCupSize(coffeeMaker, coffee);
        } 
        
    }

    private void subtractionOfIngredients() {
        coffeeMaker.status.setWaterLevel(coffeeMaker.status.getWaterLevel() - coffee.getAmountOfWater() * quantity);
        coffeeMaker.status.setMilkLevel(coffeeMaker.status.getMilkLevel() - coffee.getAmountMilk() * quantity);
        coffeeMaker.status.setCoffeeBeansLevel((float) (coffeeMaker.status.getCoffeeBeansLevel() - coffee.getAmountOfCoffee() * quantity));
        coffeeMaker.status.setGroundContainer(quantity);
        coffeeMaker.status.setCoffeeCounter(quantity);

    }


}
