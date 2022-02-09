package com.tabram.coffemaker;
import com.tabram.coffemaker.Pages.LoginPage;

public class CoffeeMaker {
    private Status status;
    private MachineAction machineAction;
    private LoginPage loginPage;

    public CoffeeMaker() {
        this.status = new Status();
        this.machineAction = new MachineAction();
        this.loginPage = new LoginPage();
    }

    public static void main(String[] args) {

        CoffeeMaker coffeeMaker = new CoffeeMaker();

        coffeeMaker.machineAction.turnOnCaffeeMaker();
        coffeeMaker.machineAction.turnOffCaffeeMaker();

        coffeeMaker.status.checkStatus();




    }
}