package com.tabram.coffeemaker.api;

import com.tabram.coffeemaker.dto.RoleToUserForm;
import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserResourceController {
    private final UserService userService;

    public UserResourceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/find/{username}")
    public ResponseEntity<User> getUserById(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/add-role")
    public ResponseEntity<String> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().body("The role " + form.getRoleName() + "has been added to user " + form.getUsername());
    }

    @DeleteMapping("/user/role/delete")
    public ResponseEntity<String> deleteRoleFromUser(@RequestBody RoleToUserForm form) {
        userService.deleteRoleFromUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().body("The role " + form.getRoleName() + "has been removed from user: " + form.getUsername());
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) {
        User user = userService.updateUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/deactivate/{username}")
    public ResponseEntity<String> deactivationUser(@PathVariable("username") String username) {
        userService.deactivationUser(username);
        return new ResponseEntity<>("The user has been successfully deactivated", HttpStatus.OK);
    }
}
