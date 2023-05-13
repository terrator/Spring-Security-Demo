package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.Authority;

import java.util.List;

public interface AuthorityServiceInterface {

    public List<Authority> getAuthorities();
    public void save(Authority authority);
    public Authority findByAuthority(String name);
}
