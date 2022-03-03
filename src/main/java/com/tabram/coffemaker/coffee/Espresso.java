package com.tabram.coffemaker.coffee;

import com.tabram.coffemaker.Coffee;

import javax.persistence.Entity;

@Entity
public class Espresso extends Coffee {
    public Espresso() {
        super("Espresso", 95, 5, 17.5, 40, 0, 100);
    }
}
