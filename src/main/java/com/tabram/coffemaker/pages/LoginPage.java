package com.tabram.coffemaker.pages;
import java.util.HashMap;
import java.util.Scanner;

public class LoginPage {
    public static String id;
    HashMap<String,String> loginInfo;
    boolean logSuccess;


    public LoginPage(HashMap<String, String> loginInfoOriginal){
        loginInfo = loginInfoOriginal;

        System.out.println("\n------------------------- ");
        System.out.println("Enter your ID and NAME.");
        System.out.println("-------------------------");

        while (!logSuccess){
            Scanner sc = new Scanner(System.in);
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

    }



}
