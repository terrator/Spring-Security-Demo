package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;

import java.util.List;

public interface UserServiceInterface {
    public List<SecurityUser> geUsers();
    public void save(SecurityUser user);
    public SecurityUser findByEmail(String email);
    public SecurityUser findById(Long id);
}
