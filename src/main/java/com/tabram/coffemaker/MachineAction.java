package com.tabram.coffemaker;

public class MachineAction {
    static int rinsing = 60;

    protected void turnOnCaffeeMaker() {
        System.out.println("Turn-ON");
        System.out.println("Rinsing... -" + rinsing + "ml");
        Status.waterLevel -= rinsing;
        System.out.println("Heating turned ON.");

    }

    public static void turnOffCaffeeMaker() {
        System.out.println("Rinsing... -" + rinsing + "ml");
        Status.waterLevel -= rinsing;
        System.out.println("Turn-OFF");
    }

    public void fillWater(){
        Status.waterLevel = 1500;
    }
    public void fillMilk(){
        Status.milkLevel = 1000;
    }
    public void fillCoffeeBeans(){
        Status.coffeeBeansLevel = 300;
    }
    public void emptyGroundContainer(){
        Status.groundContainer = 0;
    }
    public void descale(){
        Status.coffeeCounter = 1;
    }

}
