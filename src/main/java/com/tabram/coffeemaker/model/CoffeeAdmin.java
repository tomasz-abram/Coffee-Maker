package com.tabram.coffeemaker.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffee_admin", uniqueConstraints = {@UniqueConstraint(columnNames = {"name_of_coffee", "user_id"})})
public class CoffeeAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_of_coffee")
    private String nameOfCoffee;
    @Column(name = "temp_water")
    private int tempWater;
    @Column(name = "grinding_level")
    private int grindingLevel;
    @Column(name = "amount_of_coffee")
    private double amountOfCoffee;
    @Column(name = "amount_of_water")
    private int amountOfWater;
    @Column(name = "amount_milk")
    private int amountMilk;
    @Column(name = "temp_milk")
    private int tempMilk;
    @Column(name = "cup_size")
    private int cupSize;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public CoffeeAdmin(List<CoffeeAdmin> all) {
    }

    public CoffeeAdmin() {

    }

    public CoffeeAdmin(String nameOfCoffee, int tempWater, int grindingLevel, double amountOfCoffee, int amountOfWater, int amountMilk, int tempMilk, int cupSize) {
        this.nameOfCoffee = nameOfCoffee;
        this.tempWater = tempWater;
        this.grindingLevel = grindingLevel;
        this.amountOfCoffee = amountOfCoffee;
        this.amountOfWater = amountOfWater;
        this.amountMilk = amountMilk;
        this.tempMilk = tempMilk;
        this.cupSize = cupSize;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfCoffee() {
        return nameOfCoffee;
    }

    public void setNameOfCoffee(String nameOfCoffee) {
        this.nameOfCoffee = nameOfCoffee;
    }

    public int getTempWater() {
        return tempWater;
    }

    public void setTempWater(int tempWater) {
        this.tempWater = tempWater;
    }

    public int getGrindingLevel() {
        return grindingLevel;
    }

    public void setGrindingLevel(int grindingLevel) {
        this.grindingLevel = grindingLevel;
    }

    public double getAmountOfCoffee() {
        return amountOfCoffee;
    }

    public void setAmountOfCoffee(double amountOfCoffee) {
        this.amountOfCoffee = amountOfCoffee;
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public int getAmountMilk() {
        return amountMilk;
    }

    public void setAmountMilk(int amountMilk) {
        this.amountMilk = amountMilk;
    }

    public int getCupSize() {
        return cupSize;
    }

    public void setCupSize(int cupSize) {
        this.cupSize = cupSize;
    }

    public int getTempMilk() {
        return tempMilk;
    }

    public void setTempMilk(int tempMilk) {
        this.tempMilk = tempMilk;
    }
}
