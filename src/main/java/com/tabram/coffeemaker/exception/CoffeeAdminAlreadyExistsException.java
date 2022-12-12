package com.tabram.coffeemaker.exception;

public class CoffeeAdminAlreadyExistsException extends RuntimeException{
    public CoffeeAdminAlreadyExistsException(String message) {
        super(message);
    }
}
