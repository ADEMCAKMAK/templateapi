package com.springboot.template.core.security;

import com.springboot.template.core.authentication.entity.SpringUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;


public final class SecurityContexts {

    public static String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof SpringUser) {
                SpringUser user = (SpringUser) authentication.getPrincipal();
                return user.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                return SecureConstants.ANONYMOUS;
            }
        }
        return SecureConstants.SYSTEM;
    }

    public static String getCurrentUserId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof SpringUser) {
                SpringUser user = (SpringUser) authentication.getPrincipal();
                return user.getUserId();
            } else if (authentication.getPrincipal() instanceof String) {
                return SecureConstants.ANONYMOUS;
            }
        }
        return SecureConstants.SYSTEM;
    }

    public static boolean isAuthenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    public static Collection<? extends GrantedAuthority> getAuthorities() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getAuthorities() : Collections.emptySet();
    }
}
