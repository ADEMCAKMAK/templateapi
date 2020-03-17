package com.springboot.template.core.web.rest.model;

import com.springboot.template.core.security.jwt.JWTConfigurer;
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
