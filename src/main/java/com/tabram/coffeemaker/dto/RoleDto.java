package com.tabram.coffeemaker.dto;

public class RoleDto {
    String roleName;

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
