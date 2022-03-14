package com.tabram.coffeemaker.user;

import com.tabram.coffeemaker.coffee.Coffee;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "User")
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

    private String userName;
    private String password;
    private boolean active;
    private String roles;

//    @ManyToMany(mappedBy = "user")
//    private Set<Coffee> coffee;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, boolean active, String roles) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public java.util.Set<Coffee> getCoffee() {
//        return coffee;
//    }
//
//    public void setCoffee(java.util.Set<Coffee> coffee) {
//        this.coffee = coffee;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
//                ", coffee=" + coffee +
                '}';
    }
}