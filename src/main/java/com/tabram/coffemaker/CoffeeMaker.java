package com.tabram.coffemaker;

public class CoffeeMaker {
    private Status status;
    private MachineAction machineAction;

    public CoffeeMaker() {
        this.status = new Status();
        this.machineAction = new MachineAction();
    }

    public static void main(String[] args) {

        CoffeeMaker coffeeMaker = new CoffeeMaker();

        coffeeMaker.machineAction.turnOnCaffeeMaker();
        coffeeMaker.machineAction.turnOffCaffeeMaker();

        coffeeMaker.status.checkStatus();




    }
}