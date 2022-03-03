package com.tabram.coffemaker.coffee;

import com.tabram.coffemaker.Coffee;

import javax.persistence.Entity;

@Entity
public class LatteMacchiato extends Coffee {
    public LatteMacchiato() {
        super("LatteMacchiato", 95, 5, 17.5, 40, 120, 100);
    }
}

