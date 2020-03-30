package com.springboot.template.core.entity;

import com.springboot.template.core.entity.base.ValueEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Audited
@Entity
@Table(name = "Z_ROLE")
public class Role extends ValueEntity {

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "Z_ROLE_AUTHORITY",
            uniqueConstraints = @UniqueConstraint(columnNames = {"ROLE_ID", "AUTHORITY_ID"}),
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    private Set<Authority> authorities = new HashSet<>(0);

    public Role() {
    }

    public Role(String code, String text) {
        super(code, text);
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
