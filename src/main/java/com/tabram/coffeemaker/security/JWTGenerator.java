package com.tabram.coffeemaker.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.tabram.coffeemaker.security.SecurityConstants.JWT_EXPIRATION;
import static com.tabram.coffeemaker.security.SecurityConstants.JWT_SECRET;

@Component
public class JWTGenerator {
    private final AuthenticationManager authenticationManager;

    public JWTGenerator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String login(String username, String password) {
        return generateToken(attemptAuthentication(username, password));
    }

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

    public Authentication attemptAuthentication(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
