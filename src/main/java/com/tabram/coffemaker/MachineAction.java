package com.tabram.coffemaker;


public class MachineAction {
    private int rinsing = 60;

    
    public void turnOnCoffeeMaker(CoffeeMaker coffeeMaker) {

        System.out.println("Turn-ON");
        System.out.println("Rinsing... -" + rinsing + "ml");
        coffeeMaker.status.setWaterLevel(coffeeMaker.status.getWaterLevel() - rinsing);
        System.out.println("Heating turned ON.");

    }

    public void turnOffCoffeeMaker(CoffeeMaker coffeeMaker) {
        System.out.println("Rinsing... -" + rinsing + "ml");
        coffeeMaker.status.setWaterLevel(coffeeMaker.status.getWaterLevel() - rinsing);
        System.out.println("Turn-OFF");
    }

    public void fillWater(CoffeeMaker coffeeMaker) {
        coffeeMaker.status.setWaterLevel(1500);
    }

    public void fillMilk(CoffeeMaker coffeeMaker) {
        coffeeMaker.status.setMilkLevel(1000);
    }

    public void fillCoffeeBeans(CoffeeMaker coffeeMaker) {
        coffeeMaker.status.setCoffeeBeansLevel(300);
    }

    public void emptyGroundContainer(CoffeeMaker coffeeMaker) {
        coffeeMaker.status.setGroundContainer(0);
    }

    public void descale(CoffeeMaker coffeeMaker) {
        coffeeMaker.status.setCoffeeCounter(0);
    }

}
