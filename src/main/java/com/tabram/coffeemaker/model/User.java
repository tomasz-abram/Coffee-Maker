package com.tabram.coffeemaker.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", unique = true)
    private String userName;
    private String password;
    private boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<CoffeeUser> coffeeUser;

    public User() {
    }

    public User(List<CoffeeUser> coffeeUser) {
        this.coffeeUser = coffeeUser;
    }

    public User(String userName, String password, boolean isActive, Collection<Role> roles, List<CoffeeUser> coffeeUser) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
        this.coffeeUser = coffeeUser;
    }

    public User(String userName, String password, boolean isActive, Collection<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<CoffeeUser> getCoffeeUser() {
        return coffeeUser;
    }

    public void setCoffeeUser(List<CoffeeUser> coffeeUser) {
        this.coffeeUser = coffeeUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}