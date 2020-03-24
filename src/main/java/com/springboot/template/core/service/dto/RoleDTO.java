package com.springboot.template.core.service.dto;

import com.springboot.template.core.entity.Authority;
import com.springboot.template.core.service.dto.ValueDTO;

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
