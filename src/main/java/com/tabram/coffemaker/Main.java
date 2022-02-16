package com.tabram.coffemaker;

public class Main {

    public static void main(String[] args) {

        CoffeeMaker coffeeMaker1 = new CoffeeMaker();
        coffeeMaker1.machineAction.turnOnCoffeeMaker(coffeeMaker1);
        coffeeMaker1.status.checkStatus();
        coffeeMaker1.log();
    }
}
