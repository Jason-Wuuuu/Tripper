package org.example.tripperbackend.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final String userId;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId) {
        super(username, password, true, true, true, true, authorities); // Adjusted for enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
