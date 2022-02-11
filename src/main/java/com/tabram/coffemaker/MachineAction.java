package com.tabram.coffemaker;

public class MachineAction {
    private int rinsing = 60;

    
    public void turnOnCoffeeMaker() {

        System.out.println("Turn-ON");
        System.out.println("Rinsing... -" + rinsing + "ml");
        CoffeeMaker.status.setWaterLevel(CoffeeMaker.status.getWaterLevel() - rinsing);
        System.out.println("Heating turned ON.");

    }

    public void turnOffCoffeeMaker() {
        System.out.println("Rinsing... -" + rinsing + "ml");
        CoffeeMaker.status.setWaterLevel(CoffeeMaker.status.getWaterLevel() - rinsing);
        System.out.println("Turn-OFF");
    }

    public void fillWater(){
        CoffeeMaker.status.setWaterLevel(1500);
    }
    public void fillMilk(){
        CoffeeMaker.status.setMilkLevel(1000);
    }
    public void fillCoffeeBeans() {
        CoffeeMaker.status.setCoffeeBeansLevel(300);
    }
    public void emptyGroundContainer(){
        CoffeeMaker.status.setGroundContainer(0);
    }
    public void descale(){
        CoffeeMaker.status.setCoffeeCounter(0);
    }

}
