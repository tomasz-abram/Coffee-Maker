package com.tabram.coffeemaker.dto;

public class CoffeeMachineConstantValueDto {

    private Long id;
    private String name;
    private Integer value;

    public CoffeeMachineConstantValueDto() {
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
