package com.tabram.coffeemaker.exception;

public class UserAlreadyExistAuthenticationException extends RuntimeException{
    public UserAlreadyExistAuthenticationException(String msg) {
        super(msg);
    }
}
