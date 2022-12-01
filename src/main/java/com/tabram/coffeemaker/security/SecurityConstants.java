package com.tabram.coffeemaker.security;

class SecurityConstants {

    public static final long JWT_EXPIRATION = 1000L * 60L * 1000L;
    public static final String JWT_SECRET = "secret";

    private SecurityConstants() {
    }
}
