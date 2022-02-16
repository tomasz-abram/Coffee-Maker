package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;
import com.tabram.coffemaker.ScannerSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeSettings {
    private int i;
    private int setSettings;
    private ScannerSettings scanner = new ScannerSettings();

    public ChangeSettings(CoffeeMaker coffeeMaker, Coffee coffee) {
        System.out.println("Settings: " + coffee.getNameOfCoffee());
        List<String> aList = new ArrayList<>();
        aList.add("Temp. water: " + coffee.getTempWater());
        aList.add("Grinding Level: " + coffee.getGrindingLevel());
        aList.add("The amount of coffee beans: " + coffee.getAmountOfCoffee());
        aList.add("Amount of water: " + coffee.getAmountOfWater());
        aList.add("Amount of milk: " + coffee.getAmountMilk());
        aList.add("Cup size: " + coffee.getCupSize());
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
                scanner.scanner();
                scanner.checkScanner("Temp. water",65,"65C\u00B0",96,"96C\u00B0");
                coffee.setTempWater((int) scanner.getSetSettings());
                new ChangeSettings(coffeeMaker, coffee);
                break;
            case 2:
                scanner.scanner();
                scanner.checkScanner("Grinding level",1,"1",10,"10");
                coffee.setGrindingLevel((int) scanner.getSetSettings());
                new ChangeSettings(coffeeMaker, coffee);
                break;
            case 3:
                scanner.scanner();
                scanner.checkScanner("Amount of coffee beans",10,"10g",30,"30g");
                coffee.setAmountOfCoffee(scanner.getSetSettings());
                new ChangeSettings(coffeeMaker, coffee);
                break;
            case 4:
                scanner.scanner();
                scanner.checkScanner("Amount of water",10,"10ml",500,"400ml");
                coffee.setAmountOfWater((int) scanner.getSetSettings());
                new ChangeSettings(coffeeMaker, coffee);
                break;
            case 5:
                scanner.scanner();
                scanner.checkScanner("Amount of milk",0,"0ml",500,"500ml");
                coffee.setAmountMilk((int) scanner.getSetSettings());
                new ChangeSettings(coffeeMaker, coffee);
                break;
            case 6:
                scanner.scanner();
                scanner.checkScanner("Cup size:",30,"30ml",1000,"1000ml");
                coffee.setCupSize((int) scanner.getSetSettings());
                new ChangeSettings(coffeeMaker, coffee);
                break;
            case 7:
                new SettingsPage(coffeeMaker);
                break;
            default:
                System.err.println("Enter a range between 1 - " + aList.size());
                new SelectCoffeePage(coffeeMaker);

        }
    }

    }


