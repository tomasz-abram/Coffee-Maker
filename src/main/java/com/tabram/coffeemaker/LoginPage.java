//package com.tabram.coffeemaker;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//
//public class LoginPage {
//    HashMap<String, String> loginInfo;
//    boolean logSuccess;
//    private int i;
//
//
//    public LoginPage(CoffeeMaker coffeeMaker, HashMap<String, String> loginInfoOriginal) {
//        loginInfo = loginInfoOriginal;
//
//        System.out.println("\n---------------------------------------------  ");
//        System.out.println("Coffee Maker T-2000");
//        System.out.println("--------------------------------------------- ");
//
//        List<String> aList = new ArrayList<>();
//        aList.add("Login");
//        aList.add("Default user");
//        aList.add("New user");
//        aList.add("Turn-off");
//
//        for (String element : aList) {
//            i++;
//            System.out.println("-------------------------------- ");
//            System.out.println(i + ") " + element);
//        }
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your choice.");
//        int choice = sc.nextInt();
//
//
//        switch (choice) {
//            case 1:
//
//                sc = new Scanner(System.in);
//                System.out.println("Enter your ID.");
//                String id = sc.nextLine();
//                System.out.println("Enter your Name");
//                String name = sc.nextLine();
//
//                if (loginInfo.containsKey(id)) {
//                    if (loginInfo.get(id).equals(name)) {
//                        System.out.println("Login successful!");
//                        logSuccess = true;
//                        MenuPage menuPage = new MenuPage(coffeeMaker, name);
//                    } else {
//                        System.out.println("Wrong ID or Name!");
//                    }
//
//                } else {
//                    System.out.println("Id not found.");
//                }
//
//                LoginPage loginPage = new LoginPage(coffeeMaker, loginInfoOriginal);
//                break;
//            case 2:
//                MenuPage menuPage = new MenuPage(coffeeMaker, "Default");
//                break;
//            case 3:
//                System.out.println("Write your ID.");
//                sc = new Scanner(System.in);
//                id = sc.nextLine();
//                System.out.println("Write your name.");
//                name = sc.nextLine();
//                IdAndName newId = new IdAndName();
//                newId.setLoginInfo(coffeeMaker, id, name);
//                menuPage = new MenuPage(coffeeMaker, name);
//                break;
//            case 4:
//                coffeeMaker.machineAction.turnOffCoffeeMaker(coffeeMaker);
//                break;
//            default:
//                //  throw new UnsupportedOperationException(String message);
//                System.err.println("Enter a range between 1 - " + aList.size());
//                new SelectCoffeePage(coffeeMaker);
//        }
//
//    }
//
//    private void login() {
//
//    }
//
//    private void newUser() {
//
//    }
//
//    private void defaultUser() {
//
//    }
//
//    private void setNewUser() {
//
//
//    }
//
//
//}
//
