package com.tabram.coffeemaker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.tabram.coffeemaker.security.SecurityConstants.JWT_EXPIRATION;
import static com.tabram.coffeemaker.security.SecurityConstants.JWT_SECRET;

@Component
public class JWTGenerator {

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
        List<String> authRole = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .withClaim("roles", authRole)
                .sign(algorithm);
    }
}
