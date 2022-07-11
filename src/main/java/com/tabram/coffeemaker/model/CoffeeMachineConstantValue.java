package com.tabram.coffeemaker.model;

import org.springframework.data.keyvalue.annotation.KeySpace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@KeySpace("coffee_machine_constant_values")
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
