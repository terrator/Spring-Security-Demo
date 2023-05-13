package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {
    public List<SecurityUser> geUsers();
    public ResponseEntity save(SecurityUser user);
//    public SecurityUser findByEmail(String email);
//    public SecurityUser findById(Long id);
}
