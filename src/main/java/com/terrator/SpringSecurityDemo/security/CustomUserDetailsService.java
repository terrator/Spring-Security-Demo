package com.terrator.SpringSecurityDemo.security;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<SecurityUser> user = securityUserRepository.findByEmail(email);
        return user.map(SecurityUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
