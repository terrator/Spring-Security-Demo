package com.terrator.SpringSecurityDemo.security;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUserDetails implements UserDetails {
    private SecurityUser securityUser;
    private List<GrantedAuthority> authorities;

    public SecurityUserDetails(SecurityUser securityUser) {
        authorities = securityUser.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        this.securityUser = securityUser;
    }
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return securityUser.getPassword();
    }

    @Override
    public String getUsername() {
        return securityUser.getName();
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
