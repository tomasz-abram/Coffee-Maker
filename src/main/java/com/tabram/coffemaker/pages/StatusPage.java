package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.CoffeeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatusPage {
    private int i;
//    private Status status = new Status();
//    private MachineAction machineAction = new MachineAction();
    public StatusPage() {
        System.out.println(CoffeeMaker.status.getWaterLevel());
        System.out.println(CoffeeMaker.status.getMilkLevel());
        System.out.println(CoffeeMaker.status.getCoffeeBeansLevel());
        System.out.println(CoffeeMaker.status.getGroundContainer());
        System.out.println(CoffeeMaker.status.getScaleCounter());

        List<String> statusList = new ArrayList<>();
        statusList.add("Refill Water");
        statusList.add("Refill Milk");
        statusList.add("Refill the coffee beans");
        statusList.add("Empty the ground container");
        statusList.add("Descale");
        statusList.add("Exit");

        for (String element : statusList) {
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
                CoffeeMaker.machineAction.descale();
                new StatusPage();
                break;
            case 6:
                new MenuPage(LoginPage.id);
                break;
            default:
                System.err.println("Enter a range between 1 - 6");
                new StatusPage();


        }

    }

}
