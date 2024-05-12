package com.devandre.petsnetwork.utils;

public class SecurityConstants {

    public static final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private SecurityConstants() {
    }
}
