package com.tabram.coffemaker;

import com.tabram.coffemaker.pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class IdAndName {
    private static IdAndName instance;
    private Map<String, String> loginInfo;



    public IdAndName() {
        loginInfo = new HashMap<>();
        loginInfo.put("1", "1");
        loginInfo.put("000", "Default");
        loginInfo.put("001", "Kozinus");
        loginInfo.put("002", "Brodson");
        loginInfo.put("003", "Bromasz");

    }


    public void setLoginInfo(CoffeeMaker coffeeMaker, String id, String name) {
        if (loginInfo.containsKey(id)) {
            System.out.println("The given id already exists, enter a different one.");
            LoginPage loginPage = new LoginPage(coffeeMaker, (HashMap<String, String>) loginInfo);
        } else {
            System.out.println("Successful new user created.");
            loginInfo.put(id, name);

        }
    }

    public static IdAndName getInstance() {
        return instance;
    }

    public static IdAndName getIdAndNameInstance() {
        if (instance == null)
            instance = new IdAndName();
        return instance;
    }

    public Map<String, String> getLoginInfo() {
        return loginInfo;
    }
}

