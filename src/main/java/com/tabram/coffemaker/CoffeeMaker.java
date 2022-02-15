package com.tabram.coffemaker;

import com.tabram.coffemaker.coffee.*;
import com.tabram.coffemaker.pages.LoginPage;

import java.util.HashMap;

public class CoffeeMaker {
     public static Status status = new Status();
     public static MachineAction machineAction = new MachineAction();
    public static Coffee espresso = new Espresso();
    public static Coffee ristretto = new Ristretto();
    public static Coffee cappuccino = new Cappuccino();
    public static Coffee macchiato = new Macchiato();
    public static Coffee lungo = new Lungo();
    public static Coffee latteMacchiato = new LatteMacchiato();
    public void log() {
          IdAndName idAndName = new IdAndName();
          LoginPage loginPage = new LoginPage((HashMap<String, String>) idAndName.getLoginInfo());
      }


}