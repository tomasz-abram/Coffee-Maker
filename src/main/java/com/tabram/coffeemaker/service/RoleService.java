package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.RoleDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRole(String roleName){
        return roleRepository.findByName(roleName);
    }

    public Role saveRole(RoleDto role) {
        log.info("Saving new role: {}", role.getRoleName());
        Role newRole = new Role(role.getRoleName());
        return roleRepository.save(newRole);
    }
}
