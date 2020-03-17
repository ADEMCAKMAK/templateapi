package com.springboot.template.core.authentication.service.dto;

import com.exercises.springboot.core.authentication.entity.Authority;
import com.exercises.springboot.core.service.dto.ValueDTO;

import java.util.Set;

public class RoleDTO extends ValueDTO {

    private Set<Authority> authorities;

    private Set<String> authorityCodes;

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getAuthorityCodes() {
        return authorityCodes;
    }

    public void setAuthorityCodes(Set<String> authorityCodes) {
        this.authorityCodes = authorityCodes;
    }
}
