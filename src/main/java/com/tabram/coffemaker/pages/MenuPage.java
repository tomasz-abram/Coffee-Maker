package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.CoffeeMaker;
import com.tabram.coffemaker.IdAndName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuPage {
    private int i;

    public MenuPage(String id) {
        if (id != null) {
            System.out.println("\nHello " + id);
            System.out.println("it's time for your coffee.");
        }

        List<String> aList = new ArrayList<>();
        aList.add("Make Coffee");
        aList.add("Settings");
        aList.add("Status");
        aList.add("LogOut");
        aList.add("TurnOff");

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
                new SelectCoffeePage();
                break;
            case 2:
                new SettingsPage();
                break;
            case 3:
                new StatusPage();
                break;
            case 4:
                IdAndName idAndName = new IdAndName();
                LoginPage loginPage = new LoginPage((HashMap<String, String>) idAndName.getLoginInfo());
                break;
            case 5:
                CoffeeMaker.machineAction.turnOffCoffeeMaker();
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new MenuPage(id);
        }


    }

}
