package com.springboot.template.core.web.rest.model;

import com.exercises.springboot.core.security.jwt.JWTConfigurer;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object to return as body in JWT Authentication.
 */
public class JWTToken {

    private final String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @JsonProperty(JWTConfigurer.AUTHORIZATION_HEADER)
    public String getToken() { return token; }
}
