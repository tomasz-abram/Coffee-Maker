package com.tabram.coffeemaker.dto;

import com.tabram.coffeemaker.model.Role;

import java.util.Collection;

public class UserDto {

    private Long id;
    private String username;
    private boolean isEnabled;
    private Collection<Role> roles;

    public UserDto() {
    }

    public UserDto(Long id, String username, boolean isEnabled, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserDto(String username, boolean isEnabled, Collection<Role> roles) {
        this.username = username;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
