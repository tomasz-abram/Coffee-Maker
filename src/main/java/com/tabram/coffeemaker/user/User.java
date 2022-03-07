package com.tabram.coffeemaker.user;

import com.tabram.coffeemaker.Coffee;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "Id")
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    Long id;

    @Column
    String name;
    @ManyToOne
    @JoinColumn(name = "coffee_id")
    Coffee coffee;

    public User() {
    }

    public User(String name, Coffee coffee) {
        this.name = name;
        this.coffee = coffee;
    }

    public User(String name) {
        this.name = name;
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

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coffee=" + coffee +
                '}';
    }



    private void login() {

    }

    private void newUser() {

    }

    private void defaultUser() {

    }

    private void setNewUser() {


    }
}