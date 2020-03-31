package com.springboot.template.core.web.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.template.core.constant.AuthenticationConstants;
import com.springboot.template.core.service.dto.AuthorityDTO;
import com.springboot.template.core.service.dto.RoleDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


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

    @NotNull
    private String password;

    @Email
    @Size(max = 100)
    private String email;

    private Set<RoleDTO> roles = new HashSet<>(0);

    private Set<AuthorityDTO> authorities = new HashSet<>(0);

    public RegisterModel() {
        authorities.add(new AuthorityDTO("USER","USER"));
        roles.add(new RoleDTO("USER", "USER"));
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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }
}
