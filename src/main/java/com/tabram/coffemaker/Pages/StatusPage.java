package com.tabram.coffemaker.Pages;

import com.tabram.coffemaker.CoffeeMaker;
import com.tabram.coffemaker.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatusPage {
int i;

    public StatusPage() {
        System.out.println(Status.milkLevel);
        System.out.println(Status.waterLevel);
        System.out.println(Status.coffeeBeansLevel);
        System.out.println(Status.groundContainer);
        System.out.println(Status.scaleCounter);

        List<String> statusList = new ArrayList<>();
        statusList.add("Refill Water");
        statusList.add("Refill Milk");
        statusList.add("Refill the coffee beans");
        statusList.add("Empty the ground container");
        statusList.add("Descale");
        statusList.add("Exit");

        for (String element: statusList) {
            i++;
            System.out.println("-------------------------------- ");
            System.out.println(i + ") " + element);
        }



        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice.");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                CoffeeMaker.machineAction.fillWater();
            case 2:
                CoffeeMaker.machineAction.fillMilk();
            case 3:
                CoffeeMaker.machineAction.fillCoffeeBeans();
            case 4:
                CoffeeMaker.machineAction.emptyGroundContainer();
            case 5:
                CoffeeMaker.machineAction.descale();
            case 6:
                MenuPage menuPage = new MenuPage(LoginPage.id);


        }

    }

}
