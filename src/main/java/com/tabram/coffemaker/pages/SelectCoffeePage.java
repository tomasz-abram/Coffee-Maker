package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.CoffeeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectCoffeePage {


    public SelectCoffeePage(CoffeeMaker coffeeMaker) {
        int i = 0;
// ?????? czy można uzyskać dostęp do nazw child albo zautomatyzować proces generowania menu
        List<String> aList = new ArrayList<>();
        aList.add("Espresso");
        aList.add("Ristretto");
        aList.add("Cappuccino");
        aList.add("Macchiato");
        aList.add("Lungo / Americano");
        aList.add("Latte macchiato");
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
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffeeMaker.espresso);
                break;
            case 2:
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffeeMaker.ristretto);
                break;
            case 3:
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffeeMaker.cappuccino);
                break;
            case 4:
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffeeMaker.macchiato);
                break;
            case 5:
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffeeMaker.lungo);
                break;
            case 6:
                new DoYouHaveTheSameCupSizePage(coffeeMaker, coffeeMaker.latteMacchiato);
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
