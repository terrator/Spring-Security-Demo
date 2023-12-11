package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.User;
import com.terrator.SpringSecurityDemo.model.AuthenticationResponse;
import com.terrator.SpringSecurityDemo.model.UserModel;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SecurityUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(UserModel userModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword())
        );
        var user = repository.findByEmail(userModel.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + userModel.getEmail() + " not found"));
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse register(UserModel userModel) {
        var user = User.builder()
                .name(userModel.getName())
                .email(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .roles(userModel.getRoles())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
