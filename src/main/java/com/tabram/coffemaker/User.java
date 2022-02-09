package com.tabram.coffemaker;

import java.util.HashMap;

public class User {

    HashMap<String,String> loginInfo = new HashMap<>();

    User(){
        loginInfo.put("001", "Kozinus");
        loginInfo.put("002", "Brodson");
        loginInfo.put("003", "Bromasz");
        loginInfo.put("999", "Default");
    }
    protected HashMap <String,String> getLoginInfo(){
        return loginInfo;
    }

    NewUser(String id, String user){
        loginInfo.put(id,user);
    }

    DefaultUser(){

    }
}
