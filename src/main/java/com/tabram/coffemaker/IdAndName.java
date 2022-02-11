package com.tabram.coffemaker;

import java.util.HashMap;
import java.util.Map;

public class IdAndName {
    private static IdAndName instance;
    private Map<String, String> loginInfo;



    public IdAndName() {
        loginInfo = new HashMap<>();
        loginInfo.put("1", "1");
        loginInfo.put("001", "Kozinus");
        loginInfo.put("002", "Brodson");
        loginInfo.put("003", "Bromasz");
        loginInfo.put("999", "Default");
    }

    public void setLoginInfo(Map<String, String> loginInfo) {
        this.loginInfo = loginInfo;
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

