package com.tabram.coffemaker;

import com.tabram.coffemaker.Pages.LoginPage;

public class CoffeeMaker {

    private Status status;
    public static MachineAction machineAction;


    public CoffeeMaker() {
        this.status = new Status();
        this.machineAction = new MachineAction();


    }

    public static void main(String[] args) {

        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.machineAction.turnOnCaffeeMaker();
        coffeeMaker.status.checkStatus();
        IdAndName idAndName = new IdAndName();
        LoginPage loginPage = new LoginPage(IdAndName.getLoginInfo());

    }
}