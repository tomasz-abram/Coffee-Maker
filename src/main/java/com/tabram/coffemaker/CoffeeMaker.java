package com.tabram.coffemaker;

public class CoffeeMaker {
    private Status checkMachine;

    public CoffeeMaker() {
        this.checkMachine = new Status();
    }

    public static void main(String[] args) {

        MachineAction.turnOnCaffeeMaker();
        MachineAction.turnOffCaffeeMaker();

        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.checkMachine.checkStatus();


    }
}