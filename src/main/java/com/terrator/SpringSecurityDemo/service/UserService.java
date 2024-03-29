package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.User;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Autowired
    private PasswordEncoder encoder;

//    @Autowired
//    private AuthorityRepository authorityRepository;
    @Override
    public List<User> geUsers() {
        return securityUserRepository.findAll();
    }

    @Override
    public void save(User securityUser)  {
        securityUserRepository.save(securityUser);
    }

    @Override
    public User findByEmail(String email) {
        return securityUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
    }

    @Override
    public User findById(Long id) {
        return securityUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found"));
    }
}
