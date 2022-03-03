package com.tabram.coffemaker.coffee;

import com.tabram.coffemaker.Coffee;

import javax.persistence.Entity;

@Entity
public class Macchiato extends Coffee {
    public Macchiato() {
        super("Macchiato", 95, 5, 17.5, 40, 10, 100);
    }
}