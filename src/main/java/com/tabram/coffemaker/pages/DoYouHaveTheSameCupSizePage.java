package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoYouHaveTheSameCupSizePage {
    public DoYouHaveTheSameCupSizePage(CoffeeMaker coffeeMaker, Coffee coffee) {

        System.out.println("Cup size for " + coffee.getNameOfCoffee() + " is set to " + coffee.getCupSize() + "ml.");
        System.out.println("A single " + coffee.getNameOfCoffee() + " has the volume " + (coffee.getAmountOfWater() + coffee.getAmountMilk()) + "ml");
        System.out.println("Do you want to change the cup size settings? ");

        int i = 0;
        List<String> selectCupSizeList = new ArrayList<>();
        selectCupSizeList.add("No");
        selectCupSizeList.add("Yes");
        selectCupSizeList.add("Back");

        for (String element : selectCupSizeList) {
            i++;
            System.out.println("-------------------------------- ");
            System.out.println(i + ") " + element);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice.");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                new SetHowManyCoffeesYouWant(coffeeMaker, coffee);
                break;
            case 2:
                new ChangeCupSize(coffeeMaker, coffee);
                break;
            case 3:
                new SelectCoffeePage(coffeeMaker);
                break;

            default:
                System.err.println("Enter a range between 1 - 3");
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffee);
        }
    }
}
