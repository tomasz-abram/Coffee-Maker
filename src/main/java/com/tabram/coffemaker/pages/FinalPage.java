package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.CoffeeMaker;
import com.tabram.coffemaker.IdAndName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FinalPage {
    private int i;

    public FinalPage(CoffeeMaker coffeeMaker) {
        System.out.println("Enjoy Your Coffee!");
        System.out.println("What you want to do now?");

        List<String> aList = new ArrayList<>();
        aList.add("Go to Menu");
        aList.add("Log out");
        aList.add("Turn off");

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
                new MenuPage(coffeeMaker, null);
                break;
            case 2:
                IdAndName idAndName = new IdAndName();
                LoginPage loginPage = new LoginPage(coffeeMaker, (HashMap<String, String>) idAndName.getLoginInfo());
                break;
            case 3:
                coffeeMaker.machineAction.turnOffCoffeeMaker(coffeeMaker);
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new FinalPage(coffeeMaker);
        }
    }
}
