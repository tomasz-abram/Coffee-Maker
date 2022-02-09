package com.tabram.coffemaker.Pages;

public class SelectCoffeePage {
}


////**************************************************
//public class Main {
//
//    public static void main(String[] args) {
//
//        IDandPasswords idandPasswords = new IDandPasswords();
//
//        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
//
//    }
//}
////**************************************************
//public class IDandPasswords {
//
//    HashMap<String,String> logininfo = new HashMap<String,String>();
//
//    IDandPasswords(){
//
//        logininfo.put("Bro","pizza");
//        logininfo.put("Brometheus","PASSWORD");
//        logininfo.put("BroCode","abc123");
//    }
//
//    public HashMap getLoginInfo(){
//        return logininfo;
//    }
//}
////**************************************************
//import java.awt.*;
//        import java.awt.event.*;
//        import java.util.*;
//        import javax.swing.*;
//
//public class LoginPage implements ActionListener{
//
//
//    HashMap<String,String> logininfo = new HashMap<String,String>();
//
//    LoginPage(HashMap<String,String> loginInfoOriginal){
//
//        logininfo = loginInfoOriginal;
//
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        if(e.getSource()==loginButton) {
//
//            String userID = userIDField.getText();
//            String password = String.valueOf(userPasswordField.getPassword());
//
//            if(logininfo.containsKey(userID)) {
//                if(logininfo.get(userID).equals(password)) {
//                    messageLabel.setText("Login successful");
//                    WelcomePage welcomePage = new WelcomePage(userID);
//                }
//                else {
//                    messageLabel.setText("Wrong password");
//                }
//
//            }
//            else {
//                messageLabel.setText("username not found");
//            }
//        }
//    }
//}
////**************************************************
//import java.awt.*;
//        import javax.swing.*;
//
//public class WelcomePage {
//
//    JFrame frame = new JFrame();
//    JLabel welcomeLabel = new JLabel("Hello!");
//
//    WelcomePage(String userID){
//
//        welcomeLabel.setBounds(0,0,200,35);
//        welcomeLabel.setFont(new Font(null,Font.PLAIN,25));
//        welcomeLabel.setText("Hello "+userID);
//
//        frame.add(welcomeLabel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(420, 420);
//        frame.setLayout(null);
//        frame.setVisible(true);
//    }
//}
////*************************************************