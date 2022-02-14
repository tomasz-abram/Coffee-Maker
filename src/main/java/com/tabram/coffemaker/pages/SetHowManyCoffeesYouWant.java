package com.tabram.coffemaker.pages;

import com.tabram.coffemaker.Coffee;

import java.util.Scanner;

public class SetHowManyCoffeesYouWant {
    public SetHowManyCoffeesYouWant(Coffee coffee){

        System.out.println("Set how many coffees you want?");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of coffees.");
        int  numberOfCoffees = sc.nextInt();

        new MakeCoffee(numberOfCoffees, coffee);
    }
}
