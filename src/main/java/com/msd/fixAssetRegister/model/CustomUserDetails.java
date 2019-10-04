package com.msd.fixAssetRegister.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Create custom userdetail object using UserDetails Interface
 * Fill database User objects to User Details Object
 */
public class CustomUserDetails extends User implements UserDetails {
    /**
     * Fill User object when create Custom User Details Object
     *
     * @param user
     */
    public CustomUserDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
        SET database roles to simple grante Authority
         */
        return getRoles().stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role.getName())
        ).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
