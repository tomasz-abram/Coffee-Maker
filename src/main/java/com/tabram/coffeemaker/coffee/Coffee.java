package com.tabram.coffeemaker.coffee;

import com.tabram.coffeemaker.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coffee_table")
public class Coffee {

    @ManyToMany(mappedBy = "coffeeList")
    List<User> usersList = new ArrayList<>();

    @Id
    @Column(name = "coffee_id")
    @SequenceGenerator(
            name = "coffee_sequence",
            sequenceName = "coffee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coffee_sequence"
    )

    private int id;
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
    @Column(name = "cup_size")
    private int cupSize;

    public Coffee() {
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

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
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
