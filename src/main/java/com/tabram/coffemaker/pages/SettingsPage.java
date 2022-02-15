package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SettingsPage {
    private int i;
    private int setSettings;

    public SettingsPage() {
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
                Coffee espresso = CoffeeMaker.espresso;
                new ChangeSettings(espresso);
                break;
            case 2:
                Coffee ristretto = CoffeeMaker.ristretto;
                new ChangeSettings(ristretto);
                break;
            case 3:
                Coffee cappuccino = CoffeeMaker.cappuccino;
                new ChangeSettings(cappuccino);
                break;
            case 4:
                Coffee macchiato = CoffeeMaker.macchiato;
                new ChangeSettings(macchiato);
                break;
            case 5:
                Coffee lungo = CoffeeMaker.lungo;
                new ChangeSettings(lungo);
                break;
            case 6:
                Coffee latteMacchiato = CoffeeMaker.latteMacchiato;
                new ChangeSettings(latteMacchiato);
                break;
            case 7:
                new MenuPage(null);
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new SelectCoffeePage();
        }


    }


}

