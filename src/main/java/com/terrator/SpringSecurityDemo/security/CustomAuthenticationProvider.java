package com.terrator.SpringSecurityDemo.security;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserRepository securityUserRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        SecurityUser user = securityUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("SecurityUser not found"));

        if (encoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, getAuthorities(user.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid Credentials");
        }
//        return null;
    }

    private @NotNull Set<SimpleGrantedAuthority> getAuthorities(@NotNull List<String> authorities) {
        Set<SimpleGrantedAuthority> list = new HashSet<>();

        authorities.forEach(auth -> list.add(new SimpleGrantedAuthority(auth)));
        return list;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }

//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//    }
//
//    public void setPasswordEncoder(PasswordEncoder encoder) {
//    }
}
