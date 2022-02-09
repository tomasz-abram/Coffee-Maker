package com.tabram.coffemaker.Pages;

import java.util.HashMap;
import java.util.Scanner;

public class LoginPage {
    HashMap<String,String> loginInfo = new HashMap<>();

     void login(HashMap<String,String> loginInfoIn) {
        HashMap<String, String> loginInfo = new HashMap<>();
        loginInfo = loginInfoIn;

        System.out.println("------------------ ");
        System.out.println("Enter your id and name.");
        System.out.println(" ------------------ ");


        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your ID");
        String id = sc.nextLine();
        System.out.println("Enter your Name");
        String login = sc.nextLine();

        if(loginInfo.get(id).equals(login)){
            System.out.println("Login successful");
        } else {
            System.out.println("Wrong password");
        }


    }
}
