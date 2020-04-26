package com.springboot.template.core.web.rest.model;

import com.springboot.template.core.constant.AuthenticationConstants;
import com.springboot.template.core.service.dto.BaseDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserUpdateModel extends BaseDTO<String> {

    private String id;

    @Size(max = 50)
    @Pattern(regexp = AuthenticationConstants.NAME_TEXT_PATTERN)
    private String firstname;

    @Size(max = 50)
    @Pattern(regexp = AuthenticationConstants.NAME_TEXT_PATTERN)
    private String lastname;

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @Email
    @Size(max = 100)
    private String email;

    private String phoneNumber;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
