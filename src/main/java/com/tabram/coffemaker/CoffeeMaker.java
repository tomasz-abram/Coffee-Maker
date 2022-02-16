package com.tabram.coffemaker;

import com.tabram.coffemaker.coffee.*;
import com.tabram.coffemaker.pages.LoginPage;

import java.util.HashMap;

public class CoffeeMaker {
    public Status status = new Status();
    public MachineAction machineAction = new MachineAction();
    public Coffee espresso = new Espresso();
    public Coffee ristretto = new Ristretto();
    public Coffee cappuccino = new Cappuccino();
    public Coffee macchiato = new Macchiato();
    public Coffee lungo = new Lungo();
    public Coffee latteMacchiato = new LatteMacchiato();

    public void log() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        IdAndName idAndName = new IdAndName();
        LoginPage loginPage = new LoginPage(coffeeMaker, (HashMap<String, String>) idAndName.getLoginInfo());
    }


}