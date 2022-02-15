package com.tabram.coffemaker;

import java.util.Scanner;

public class ScannerSettings {
private float setSettings;

    public float scanner() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your settings.");
        setSettings = sc.nextFloat();
        return setSettings;
    }

    public void checkScanner(String section, int lowerRange, String textLowerRange, int highRange, String textHighRange) {
        if ((setSettings < lowerRange) || (setSettings > highRange)) {
            System.err.println("Value is out of range for " + section + "!");
            System.err.println("Set the value in the range " + textLowerRange + " - " + textHighRange + ".");
            scanner();
        }
    }

    public float getSetSettings() {
        return setSettings;
    }
}
