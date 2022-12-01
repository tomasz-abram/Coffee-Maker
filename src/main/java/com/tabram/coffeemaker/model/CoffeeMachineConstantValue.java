package com.tabram.coffeemaker.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coffee_machine_constant_value" , uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class CoffeeMachineConstantValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
@Column(name = "name")
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

    @Override
    public String toString() {
        return "CoffeeMachineConstantValue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
