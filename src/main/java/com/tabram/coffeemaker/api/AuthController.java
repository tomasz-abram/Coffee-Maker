package com.tabram.coffeemaker.api;

import com.tabram.coffeemaker.dto.AuthResponseDto;
import com.tabram.coffeemaker.dto.LoginDto;
import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.security.JWTGenerator;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JWTGenerator jwtGenerator;


    public AuthController(UserService userService, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(new AuthResponseDto(jwtGenerator.login(loginDto.getUsername(), loginDto.getPassword())), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(userService.registerUser(userRegistrationDto));
    }
}
