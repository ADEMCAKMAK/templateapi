package com.springboot.template.core.authentication.entity;

import com.exercises.springboot.core.authentication.AuthenticationConstants;
import com.exercises.springboot.core.entity.base.BaseEntity;
import com.exercises.springboot.core.entity.base.SimpleBaseEntity;
import com.exercises.springboot.core.entity.id.generator.SequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Audited
@Entity
@Table(name = "Z_USER")
public class User extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator= AuthenticationConstants.USER_GENERATOR)
    @GenericGenerator(name = AuthenticationConstants.USER_GENERATOR,
            strategy=AuthenticationConstants.GENERATOR_CLASS,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INITIAL_PARAM,
                            value = AuthenticationConstants.USER_INITIAL_VALUE),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER,
                            value = AuthenticationConstants.USER_PREFIX),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER,
                            value = AuthenticationConstants.USER_NUM_FORMAT)
            }
    )
    @Column(name = "ID")
    private String id;

    @Size(max = 50)
    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(name = "LASTNAME", length = 50)
    private String lastname;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME", length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "PASSWORD", length = 60)
    private String password;

    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100, unique = true)
    private String email;

    @NotNull
    @Column(name = "ACTIVATED", nullable = false)
    private boolean activated = false;

    @Column(name = "RESET_TOKEN")
    private String resetToken;

    @Column(name = "RESET_TOKEN_KEY")
    private String resetTokenKey;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "Z_USER_ROLE",
            uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "ROLE_ID"}),
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private Set<Role> roles = new HashSet<>(0);


    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "Z_USER_AUTHORITY",
            uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "AUTHORITY_ID"}),
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    private Set<Authority> authorities = new HashSet<>(0);

    public User() {
    }

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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

    @Override
    public String toString() {
        return String.format("User[id=%s, firstname=%s, lastname=%s, username=%s]",
                this.getId(), this.firstname, this.lastname, this.username);
    }

}
