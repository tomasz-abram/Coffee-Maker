package com.tabram.coffemaker;

import com.tabram.coffemaker.Pages.LoginPage;

public class CoffeeMaker {
    private Status status;
    private MachineAction machineAction;


    public CoffeeMaker() {
        this.status = new Status();
        this.machineAction = new MachineAction();


    }

    public static void main(String[] args) {

        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.machineAction.turnOnCaffeeMaker();
        coffeeMaker.machineAction.turnOffCaffeeMaker();
        coffeeMaker.status.checkStatus();


        IdAndName idAndName = new IdAndName();
        LoginPage loginPage = new LoginPage(IdAndName.getLoginInfo());

    }
}