package com.terrator.SpringSecurityDemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.terrator.SpringSecurityDemo.entity.User;
import com.terrator.SpringSecurityDemo.model.AuthenticationResponse;
import com.terrator.SpringSecurityDemo.model.UserModel;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import com.terrator.SpringSecurityDemo.service.AuthenticationService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class HomeController {
    private final SecurityUserRepository repository;
    private final AuthenticationService service;

    @GetMapping("/home")
    public String home() {
        return  "You are in the user home page";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String dashBoard() {
        return "You are in the user dashboard";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @NotNull UserModel userModel) {
        return ResponseEntity.ok(service.register(userModel));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @NotNull UserModel userModel) {
        return ResponseEntity.ok(service.authenticate(userModel));
    }

    @GetMapping("/profile")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<User> profile() throws JsonProcessingException {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        var user = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found"));
        return ResponseEntity.ok(user);
    }
}
