package com.tabram.coffemaker;

import com.tabram.coffemaker.Pages.LoginPage;

import java.util.HashMap;

public class CoffeeMaker {


   Status status = new Status();
    MachineAction machineAction = new MachineAction();



    public static void main(String[] args) {


        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.machineAction.turnOnCoffeeMaker();
        coffeeMaker.status.checkStatus();
        IdAndName idAndName = new IdAndName();
        LoginPage loginPage = new LoginPage((HashMap<String, String>) idAndName.getLoginInfo());




    }
}