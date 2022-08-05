package com.tabram.coffeemaker.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<CoffeeUser> coffeeUser;

    public User() {
    }

    public User(List<CoffeeUser> coffeeUser) {
        this.coffeeUser = coffeeUser;
    }

    public User(String userName, String password, boolean isEnabled) {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public User(String userName, String password, boolean isEnabled, Set<Role> roles, List<CoffeeUser> coffeeUser) {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
        this.coffeeUser = coffeeUser;
    }

    public User(String userName, String password, boolean isEnabled, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<CoffeeUser> getCoffeeUser() {
        return coffeeUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                ", coffeeUser=" + coffeeUser +
                '}';
    }
}