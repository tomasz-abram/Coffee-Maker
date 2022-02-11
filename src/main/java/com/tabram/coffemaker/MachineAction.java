package com.tabram.coffemaker;

public class MachineAction {
    private int rinsing = 60;
    private  Status status = new Status();
    
    public void turnOnCoffeeMaker() {

        System.out.println("Turn-ON");
        System.out.println("Rinsing... -" + rinsing + "ml");
        status.setWaterLevel(status.getWaterLevel() - rinsing);
        System.out.println("Heating turned ON.");

    }

    public void turnOffCoffeeMaker() {
        System.out.println("Rinsing... -" + rinsing + "ml");
        status.setWaterLevel(status.getWaterLevel() - rinsing);
        System.out.println("Turn-OFF");
    }

    public void fillWater(){
        status.setWaterLevel(1500);
    }
    public void fillMilk(){
        status.setMilkLevel(1000);
    }
    public void fillCoffeeBeans() {
        status.setCoffeeBeansLevel(300);
    }
    public void emptyGroundContainer(){
        status.setGroundContainer(0);
    }
    public void descale(){
        status.setCoffeeCounter(0);
    }

}
