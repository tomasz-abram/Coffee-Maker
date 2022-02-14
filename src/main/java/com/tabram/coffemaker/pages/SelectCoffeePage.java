package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;
import com.tabram.coffemaker.coffee.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectCoffeePage {


    public SelectCoffeePage() {
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
                Coffee espresso = new Espresso();
                new DoYouHaveTheSameCupSizePage(espresso);
                break;
            case 2:
                Coffee ristretto = new Ristretto();
                new DoYouHaveTheSameCupSizePage(ristretto);
                break;
            case 3:
                Coffee cappuccino = new Cappuccino();
                new DoYouHaveTheSameCupSizePage(cappuccino);
                break;
            case 4:
                Coffee macchiato = new Macchiato();
                new DoYouHaveTheSameCupSizePage(macchiato);
                break;
            case 5:
                Coffee lungo = new Lungo();
                new DoYouHaveTheSameCupSizePage(lungo);
                break;
            case 6:
                Coffee latteMacchiato = new LatteMacchiato();
                new DoYouHaveTheSameCupSizePage(latteMacchiato);
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
