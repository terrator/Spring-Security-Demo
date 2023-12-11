package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.User;

import java.util.List;

public interface UserServiceInterface {
    public List<User> geUsers();
    public void save(User user);
    public User findByEmail(String email);
    public User findById(Long id);
}
