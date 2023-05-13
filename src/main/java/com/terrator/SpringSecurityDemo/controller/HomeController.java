package com.terrator.SpringSecurityDemo.controller;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import com.terrator.SpringSecurityDemo.model.UserModel;
import com.terrator.SpringSecurityDemo.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class HomeController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private CustomAuthenticationProvider authenticationProvider;

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
    public List<SecurityUser> getUsers() {
        return userService.geUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @NotNull UserModel userModel) {
        ModelMapper modelMapper = new ModelMapper();
        SecurityUser securityUser = modelMapper.map(userModel, SecurityUser.class);
        securityUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userService.save(securityUser);
    }

//    @PostMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody @NotNull UserModel userModel) throws Exception {
//        Authentication authentication;
//
//        try {
//            SecurityUser user = userService.findByEmail(userModel.getEmail());
//            authentication = authenticationProvider.authenticate(
//            new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword(), new ArrayList<>()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (BadCredentialsException exception) {
//            throw new Exception("Invalid credential");
//        }
//
//        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String profile() {
        return "You are in the user profile";
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER")
//    public SecurityUser getUser(@PathVariable("id") Long id) {
//        return userService.findById(id);
//    }
}
