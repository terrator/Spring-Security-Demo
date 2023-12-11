package com.terrator.SpringSecurityDemo.security;

import com.terrator.SpringSecurityDemo.entity.User;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserRepository securityUserRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = securityUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (encoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }

    private @NotNull List<GrantedAuthority> getAuthorities(@NotNull List<String> authorities) {
        List<GrantedAuthority> list = new ArrayList<>();

        authorities.forEach(auth -> list.add(new SimpleGrantedAuthority(auth)));
        return list;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
