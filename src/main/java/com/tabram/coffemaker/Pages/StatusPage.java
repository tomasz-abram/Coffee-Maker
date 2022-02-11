package com.tabram.coffemaker.Pages;

import com.tabram.coffemaker.MachineAction;
import com.tabram.coffemaker.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatusPage {
    private int i;
    private Status status = new Status();
    private MachineAction machineAction = new MachineAction();
    public StatusPage() {
        System.out.println(status.getWaterLevel());
        System.out.println(status.getMilkLevel());
        System.out.println(status.getCoffeeBeansLevel());
        System.out.println(status.getGroundContainer());
        System.out.println(status.getScaleCounter());

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
                status.setWaterLevel(1500);
                System.out.println("TEST: " + status.getWaterLevel());
                new StatusPage();
                break;
            case 2:
                machineAction.fillMilk();
                new StatusPage();
                break;
            case 3:
                machineAction.fillCoffeeBeans();
                new StatusPage();
                break;
            case 4:
                machineAction.emptyGroundContainer();
                new StatusPage();
                break;
            case 5:
                machineAction.descale();
                new StatusPage();
                break;
            case 6:
                MenuPage menuPage = new MenuPage(LoginPage.id);
                break;
            default:
                throw new UnsupportedOperationException();

        }

    }

}
