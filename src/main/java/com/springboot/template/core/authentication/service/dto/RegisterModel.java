package com.springboot.template.core.authentication.service.dto;

import com.exercises.springboot.core.authentication.AuthenticationConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class RegisterModel {

    @Size(max = 50)
    @Pattern(regexp= AuthenticationConstants.NAME_TEXT_PATTERN)
    private String firstname;

    @Size(max = 50)
    @Pattern(regexp=AuthenticationConstants.NAME_TEXT_PATTERN)
    private String lastname;

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    private String password;

    @Email
    @Size(max = 100)
    private String email;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
