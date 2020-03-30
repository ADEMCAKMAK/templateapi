package com.springboot.template.core.service.dto;

import com.springboot.template.core.web.rest.model.UserUpdateModel;

import java.util.HashSet;
import java.util.Set;

public class UserDTO extends UserUpdateModel {

    private String password;

    private boolean activated;

    private String resetToken;

    private String resetUUID;

    private Set<RoleDTO> roles = new HashSet<>(0);

    private Set<AuthorityDTO> authorities = new HashSet<>(0);

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

    public String getResetUUID() {
        return resetUUID;
    }

    public void setResetUUID(String resetUUID) {
        this.resetUUID = resetUUID;
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
