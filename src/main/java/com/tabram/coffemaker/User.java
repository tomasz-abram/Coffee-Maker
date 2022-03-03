package com.tabram.coffemaker;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    // @GenericGenerator(name = "incrementor", strategy = "increment")
    private Integer id;
    @Column
    private String name;
    // private List<Coffee> coffeeList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Coffee> getCoffeeList() {
//        return coffeeList;
//    }
//
//    public void setCoffeeList(List<Coffee> coffeeList) {
//        this.coffeeList = coffeeList;
//    }


}
