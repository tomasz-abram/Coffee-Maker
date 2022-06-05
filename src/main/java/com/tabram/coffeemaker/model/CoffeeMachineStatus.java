package com.tabram.coffeemaker.model;


import javax.persistence.*;

@Entity
@Table(name = "coffee_machine_status")
public class CoffeeMachineStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "water_level")
    private int waterLevel;
    @Column(name = "milk_level")
    private int milkLevel;
    @Column(name = "coffee_beans_level")
    private float coffeeBeansLevel;
    @Column(name = "ground_container_level")
    private int groundContainerLevel;
    @Column(name = "water_hardnes")
    private float waterHardness;

    public CoffeeMachineStatus(int waterLevel, int milkLevel, float coffeeBeansLevel, int groundContainerLevel, float waterHardness) {
        this.waterLevel = waterLevel;
        this.milkLevel = milkLevel;
        this.coffeeBeansLevel = coffeeBeansLevel;
        this.groundContainerLevel = groundContainerLevel;
        this.waterHardness = waterHardness;
    }

    public CoffeeMachineStatus() {

    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getMilkLevel() {
        return milkLevel;
    }

    public void setMilkLevel(int milkLevel) {
        this.milkLevel = milkLevel;
    }

    public float getCoffeeBeansLevel() {
        return coffeeBeansLevel;
    }

    public void setCoffeeBeansLevel(float coffeeBeansLevel) {
        this.coffeeBeansLevel = coffeeBeansLevel;
    }

    public int getGroundContainerLevel() {
        return groundContainerLevel;
    }

    public void setGroundContainerLevel(int groundContainerLevel) {
        this.groundContainerLevel = groundContainerLevel;
    }

    public float getWaterHardness() {
        return waterHardness;
    }

    public void setWaterHardness(float waterHardness) {
        this.waterHardness = waterHardness;
    }
}
