package com.terrator.SpringSecurityDemo.controller;

import com.terrator.SpringSecurityDemo.entity.Authority;
//import com.terrator.SpringSecurityDemo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
//    @Autowired
//    AuthorityService authorityService;

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminHome() {
        return "You are in the admin home page";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminDashboard() {
        return "You are in the admin dashboard page";
    }

//    @GetMapping("/authorities")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN")
//    public List<Authority> authorities() {
//        return authorityService.getAuthorities();
//    }
}
