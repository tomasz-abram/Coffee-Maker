package com.tabram.coffeemaker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping //Read
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping //Create
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(
            @PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}") //Update
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name) {
        userService.updateUser(userId, name);
    }

}
