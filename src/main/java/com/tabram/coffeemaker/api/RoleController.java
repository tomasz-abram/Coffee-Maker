package com.tabram.coffeemaker.api;

import com.tabram.coffeemaker.dto.RoleDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/admin")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole(@RequestBody RoleDto role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }
}
