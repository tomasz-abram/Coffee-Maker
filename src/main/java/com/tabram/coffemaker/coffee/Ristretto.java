package com.tabram.coffemaker.coffee;

import com.tabram.coffemaker.Coffee;

import javax.persistence.Entity;

@Entity
public class Ristretto extends Coffee {
    public Ristretto() {
        super("Ristretto", 95, 5, 17.5, 20, 0, 100);
    }
}