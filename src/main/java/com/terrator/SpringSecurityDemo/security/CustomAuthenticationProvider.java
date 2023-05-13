package com.terrator.SpringSecurityDemo.security;

import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
//import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SecurityUserRepository securityUserRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        SecurityUser user = securityUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("SecurityUser not found"));
//
//        if (passwordEncoder.matches(password, user.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(email, password, getAuthorities(user.getAuthorities()));
//        } else {
//            throw new BadCredentialsException("Invalid Credentials");
//        }
        return null;
    }

//    private @NotNull Set<SimpleGrantedAuthority> getAuthorities(@NotNull Set<Authority> authorities) {
//        Set<SimpleGrantedAuthority> list = new HashSet<>();
//
//        authorities.stream().forEach(auth -> list.add(new SimpleGrantedAuthority(auth.getAuthority())));
//        return list;
//    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//    }
//
//    public void setPasswordEncoder(PasswordEncoder encoder) {
//    }
}
