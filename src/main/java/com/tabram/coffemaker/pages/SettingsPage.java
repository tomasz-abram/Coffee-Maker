package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SettingsPage {
    private int i;
    private int setSettings;

    public SettingsPage(CoffeeMaker coffeeMaker) {
//        System.err.println("Nothing to do yet...");
//        new MenuPage(null);

        System.out.println("Change settings: ");

        List<String> aList = new ArrayList<>();
        aList.add("Settings: Espresso");
        aList.add("Settings: Ristretto");
        aList.add("Settings: Cappuccino");
        aList.add("Settings: Macchiato");
        aList.add("Settings: Lungo / Americano");
        aList.add("Settings: Latte macchiato");
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
                new ChangeSettings(coffeeMaker, coffeeMaker.espresso);
                break;
            case 2:
                new ChangeSettings(coffeeMaker, coffeeMaker.ristretto);
                break;
            case 3:
                new ChangeSettings(coffeeMaker, coffeeMaker.cappuccino);
                break;
            case 4:
                new ChangeSettings(coffeeMaker, coffeeMaker.macchiato);
                break;
            case 5:
                new ChangeSettings(coffeeMaker, coffeeMaker.lungo);
                break;
            case 6:
                new ChangeSettings(coffeeMaker, coffeeMaker.latteMacchiato);
                break;
            case 7:
                new MenuPage(coffeeMaker, null);
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new SelectCoffeePage(coffeeMaker);
        }


    }


}

