package com.tabram.coffemaker;

public class MachineAction {
    static int rinsing = 60;

    protected void turnOnCaffeeMaker() {
        System.out.println("Turn-ON");
        System.out.println("Rinsing... -" + rinsing + "ml");
        Status.waterLevel -= rinsing;
        System.out.println("Heating turned ON.");

    }

    protected void turnOffCaffeeMaker() {
        System.out.println("Rinsing... -" + rinsing + "ml");
        Status.waterLevel -= rinsing;
        System.out.println("Turn-OFF");
    }

    protected void fillWater(){
        Status.waterLevel = 1500;
    }
    protected void fillMilk(){
        Status.milkLevel = 1000;
    }
    protected void fillCoffeeBeans(){
        Status.coffeeBeansLevel = 300;
    }
    protected void emptyGroundContainer(){
        Status.groundContainer = 0;
    }
    protected void descale(){
        Status.coffeeCounter = 0;
    }

}
