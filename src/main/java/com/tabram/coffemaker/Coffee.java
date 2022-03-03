package com.tabram.coffemaker;

import javax.persistence.*;

@Entity
@Table(name = "coffee_table")
public class Coffee {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //   @GeneratedValue(generator = "incrementor")
    private int id;
    private String nameOfCoffee;
    private int tempWater;
    private int grindingLevel;
    private double amountOfCoffee;
    private int amountOfWater;
    private int amountMilk;
    private int cupSize;

    public Coffee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public Coffee(String nameOfCoffee, int tempWater, int grindingLevel, double amountOfCoffee, int amountOfWater, int amountMilk, int cupSize) {
        this.setNameOfCoffee(nameOfCoffee);
        this.tempWater = tempWater;
        this.grindingLevel = grindingLevel;
        this.amountOfCoffee = amountOfCoffee;
        this.amountOfWater = amountOfWater;
        this.amountMilk = amountMilk;
        this.cupSize = cupSize;


    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", nameOfCoffee='" + nameOfCoffee + '\'' +
                ", tempWater=" + tempWater +
                ", grindingLevel=" + grindingLevel +
                ", amountOfCoffee=" + amountOfCoffee +
                ", amountOfWater=" + amountOfWater +
                ", amountMilk=" + amountMilk +
                ", cupSize=" + cupSize +
                '}';
    }
}
