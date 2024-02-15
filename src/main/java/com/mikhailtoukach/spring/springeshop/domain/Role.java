package com.mikhailtoukach.spring.springeshop.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT, ADMIN, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
