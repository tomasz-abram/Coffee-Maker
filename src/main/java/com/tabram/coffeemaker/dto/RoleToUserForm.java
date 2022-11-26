package com.tabram.coffeemaker.dto;

import java.util.Objects;

public class RoleToUserForm {
    private String username;
    private String roleName;

    public RoleToUserForm(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleToUserForm{" +
                "username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleToUserForm that = (RoleToUserForm) o;
        return username.equals(that.username) && roleName.equals(that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, roleName);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
