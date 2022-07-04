package com.tabram.coffeemaker.model;


import javax.persistence.*;

@Entity
@Table(name = "coffee_machine_stock")
public class CoffeeMachineStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true)
    private String name;
    @Column
    private float value;
    @Column
    private String units;
    @Column
    private String status;
    public CoffeeMachineStock() {
    }

    public CoffeeMachineStock(String name, float value, String units, String status) {
        this.name = name;
        this.value = value;
        this.units = units;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
