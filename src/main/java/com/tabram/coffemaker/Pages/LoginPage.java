package com.tabram.coffemaker.Pages;
import java.util.HashMap;
import java.util.Scanner;

public class LoginPage {
    HashMap<String,String> loginInfo = new HashMap<>();



    public LoginPage(HashMap<String, String> loginInfoOriginal){
        loginInfo = loginInfoOriginal;

        System.out.println("\n------------------------- ");
        System.out.println("Enter your ID and NAME.");
        System.out.println("-------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your ID.");
        String id = sc.nextLine();
        System.out.println("Enter your Name");
        String name = sc.nextLine();


        if(loginInfo.containsKey(id)) {
            if(loginInfo.get(id).equals(name)) {
                System.out.println("Login successful!");
//                MenuPage menuPage = new MenuPage(id);
            }
            else {
                System.out.println("Name");

            }

        }
        else {
            System.out.println("Id not found.");
        }

    }



}
