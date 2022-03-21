package com.tabram.coffeemaker.user;

import com.tabram.coffeemaker.coffee.Coffee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
//    @Column(name = "user_id")
//    @SequenceGenerator(
//            name = "user_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "user_sequence"
//    )
    Long id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private boolean active;
    @Column
    private String roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Coffee> coffee = new ArrayList<>();

    public List<Coffee> getCoffee() {
        return coffee;
    }

    public void setCoffee(List<Coffee> coffee) {
        this.coffee = coffee;
    }

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