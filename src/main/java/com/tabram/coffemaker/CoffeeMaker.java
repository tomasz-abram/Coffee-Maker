package com.tabram.coffemaker;

import com.tabram.coffemaker.pages.LoginPage;

import java.util.HashMap;

public class CoffeeMaker {

     public static Status status = new Status();
     public static MachineAction machineAction = new MachineAction();

    public void log() {
          IdAndName idAndName = new IdAndName();
          LoginPage loginPage = new LoginPage((HashMap<String, String>) idAndName.getLoginInfo());
      }


}