package com.springboot.template.core.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class SpringUser extends org.springframework.security.core.userdetails.User {

    private String userId;

    public SpringUser(String userId, String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public static SpringUser of(User user) {
        Set<Authority> authorities = user.getRoles()
                .stream()
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        authorities.addAll(user.getAuthorities());
        return SpringUser.of(user.getId(), user.getUsername(), user.getPassword(),
                authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getCode()))
                        .collect(Collectors.toSet()));
    }

    public static SpringUser of(String userId, String username, String password,
                                Collection<? extends GrantedAuthority> authorities) {
        return new SpringUser(userId, username, password, authorities);
    }

    public String getUserId() {
        return userId;
    }

}
