package com.springboot.template.core.service.dto;

import java.util.Set;

public class RoleDTO extends ValueDTO {

    private Set<AuthorityDTO> authorities;

    public RoleDTO() {
    }

    public RoleDTO(String code, String text) {
        super(code, text);
    }

    public Set<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

}
