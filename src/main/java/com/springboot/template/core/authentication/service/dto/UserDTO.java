package com.springboot.template.core.authentication.service.dto;


import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.authentication.entity.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserDTO extends UserUpdateModel {

    private String password;

    private boolean activated;

    private String resetToken;

    private String resetTokenKey;

    private Set<Role> roles;

    private Set<Authority> authorities;

    private Set<String> roleCodes = new HashSet<>();

    private Set<String> authorityCodes = new HashSet<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getResetTokenKey() {
        return resetTokenKey;
    }

    public void setResetTokenKey(String resetTokenKey) {
        this.resetTokenKey = resetTokenKey;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(Set<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public Set<String> getAuthorityCodes() {
        return authorityCodes;
    }

    public void setAuthorityCodes(Set<String> authorityCodes) {
        this.authorityCodes = authorityCodes;
    }
}
