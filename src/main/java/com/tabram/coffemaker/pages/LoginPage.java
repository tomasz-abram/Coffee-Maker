package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.CoffeeMaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LoginPage {
    private int i;
    HashMap<String, String> loginInfo;
    boolean logSuccess;


    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        loginInfo = loginInfoOriginal;

        System.out.println("\n---------------------------------------------  ");
        System.out.println("Coffee Maker T-2000");
        System.out.println("--------------------------------------------- ");

        List<String> aList = new ArrayList<>();
        aList.add("Login");
        aList.add("Default user");
        aList.add("New user");
        aList.add("Turn-off");

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
                while (!logSuccess) {
                    sc = new Scanner(System.in);
                    System.out.println("Enter your ID.");
                    String id = sc.nextLine();
                    System.out.println("Enter your Name");
                    String name = sc.nextLine();

                    if (loginInfo.containsKey(id)) {
                        if (loginInfo.get(id).equals(name)) {
                            System.out.println("Login successful!");
                            logSuccess = true;
                            MenuPage menuPage = new MenuPage(name);
                        } else {
                            System.out.println("Wrong ID or Name!");
                        }

                    } else {
                        System.out.println("Id not found.");
                    }
                }

                break;
            case 2:
                MenuPage menuPage = new MenuPage("Default");
                break;
            case 3:
                System.out.println("Write your name.");
                sc = new Scanner(System.in);
                String name = sc.nextLine();




                break;
            case 4:
                CoffeeMaker.machineAction.turnOffCoffeeMaker();
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new SelectCoffeePage();
        }




    }


}
