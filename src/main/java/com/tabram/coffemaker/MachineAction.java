package com.tabram.coffemaker;

public class MachineAction {
    static int rinsing = 60;

    protected static void turnOnCaffeeMaker() {
        System.out.println("Turn-ON");
        System.out.println("Rinsing... -" + rinsing + "ml");
        Status.WATER_LEVEL -= rinsing;
        System.out.println("Heating turned ON.");

    }

    protected static void turnOffCaffeeMaker() {
        System.out.println("Rinsing... -" + rinsing + "ml");
        Status.WATER_LEVEL -= rinsing;
        System.out.println("Turn-OFF");
    }
}
