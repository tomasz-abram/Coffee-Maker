package com.tabram.coffemaker.Pages;

import com.tabram.coffemaker.IdAndName;
import com.tabram.coffemaker.MachineAction;

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

        List<String> menuList = new ArrayList<>();
        menuList.add("Make Coffee");
        menuList.add("Settings");
        menuList.add("Status");
        menuList.add("LogOut");
        menuList.add("TurnOff");

        for (String element : menuList) {
            i++;
            System.out.println("-------------------------------- ");
            System.out.println(i + ") " + element);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice.");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                SelectCoffeePage selectCoffeePage = new SelectCoffeePage();
                break;
            case 2:
                SettingsPage settingsPage = new SettingsPage();
                break;
            case 3:
                StatusPage statusPage = new StatusPage();
                break;
            case 4:
                IdAndName idAndName = new IdAndName();
                LoginPage loginPage = new LoginPage((HashMap<String, String>) idAndName.getLoginInfo());
                break;
            case 5:
                MachineAction machineAction = new MachineAction();
                machineAction.turnOffCoffeeMaker();
                break;
        }


    }

}
