package com.tabram.coffemaker;

public class Main {

    public static void main(String[] args) {

        MachineAction.turnOnCaffeeMaker();
        Status.checkStatus();

        MachineAction.turnOffCaffeeMaker();
        Status.checkStatus();

//      Map<String, String> checkStatus = new Map<String, String>();
//        System.out.println(checkStatus <StatuEnums.WATER, Status_2.waterStatus>);


    }


}