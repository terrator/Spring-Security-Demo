package com.terrator.SpringSecurityDemo.controller;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import com.terrator.SpringSecurityDemo.model.UserModel;
import com.terrator.SpringSecurityDemo.security.CustomAuthenticationProvider;
import com.terrator.SpringSecurityDemo.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

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
    public ResponseEntity<?> register(@RequestBody @NotNull UserModel userModel) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            SecurityUser securityUser = modelMapper.map(userModel, SecurityUser.class);
            securityUser.setPassword(encoder.encode(userModel.getPassword()));
            userService.save(securityUser);
        }
        catch (Exception exception) {
            return new ResponseEntity<>("Unable to register; most likely duplicate email: " + userModel.getEmail(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User has been registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody @NotNull UserModel userModel) {
        try {
            SecurityUser user = userService.findByEmail(userModel.getEmail());
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getEmail(),
                        userModel.getPassword(),
                        new ArrayList<>()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User with email: " + userModel.getEmail() + " logged in.", HttpStatus.OK) ;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String profile() {
        return "You are in the user profile";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER")
    public SecurityUser getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }
}
