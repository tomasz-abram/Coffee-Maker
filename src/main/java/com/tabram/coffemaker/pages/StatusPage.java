package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.CoffeeMaker;
import com.tabram.coffemaker.ScannerSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatusPage {
    private int i;
    private ScannerSettings scanner = new ScannerSettings();
    public StatusPage() {
        System.out.println("Water level        : " + CoffeeMaker.status.getWaterLevel() + "ml.    Max level: 1500ml");
        System.out.println("Milk level         : " + CoffeeMaker.status.getMilkLevel() + "ml.    Max level: 1000ml");
        System.out.println("Coffee beans level : " + CoffeeMaker.status.getCoffeeBeansLevel() + "g.    Max level: 300g");
        System.out.println("Ground container   : " + CoffeeMaker.status.getGroundContainer() + "pcs.      Max level: 25 pieces");
        System.out.println("Water hardness     : " + CoffeeMaker.status.getWaterHardness());
        System.out.println("Scale level        : " + CoffeeMaker.status.getScaleCounter() + "        Max level: 250");

        List<String> aList = new ArrayList<>();
        aList.add("Refill Water");
        aList.add("Refill Milk");
        aList.add("Refill the coffee beans");
        aList.add("Empty the ground container");
        aList.add("Set the water hardness");
        aList.add("Descale");
        aList.add("Back");

        for (String element : aList) {
            i++;
            System.out.println("-------------------------------- ");
            System.out.println(i + ") " + element);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice.");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                CoffeeMaker.machineAction.fillWater();
                new StatusPage();
                break;
            case 2:
                CoffeeMaker.machineAction.fillMilk();
                new StatusPage();
                break;
            case 3:
                CoffeeMaker.machineAction.fillCoffeeBeans();
                new StatusPage();
                break;
            case 4:
                CoffeeMaker.machineAction.emptyGroundContainer();
                new StatusPage();
                break;
            case 5:
                scanner.scanner();
                scanner.checkScanner("Water hardness", 0 , "0", 100 , "100");
                CoffeeMaker.status.setWaterHardness(scanner.getSetSettings());
                new StatusPage();
              break;
                case 6:
                CoffeeMaker.machineAction.descale();
                new StatusPage();
                break;
            case 7:
                new MenuPage(null);
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new StatusPage();


        }

    }

}
