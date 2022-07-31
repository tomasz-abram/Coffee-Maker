package com.tabram.coffeemaker.model;

import javax.persistence.*;

@Entity
@Table(name = "coffee_machine_constant_value")
public class CoffeeMachineConstantValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer value;


    public CoffeeMachineConstantValue(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public CoffeeMachineConstantValue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
