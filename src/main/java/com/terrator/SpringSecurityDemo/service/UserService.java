package com.terrator.SpringSecurityDemo.service;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import com.terrator.SpringSecurityDemo.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<SecurityUser> geUsers() {
        return securityUserRepository.findAll();
    }

    @Override
    public ResponseEntity save(SecurityUser securityUser)  {
        try {
            securityUserRepository.save(securityUser);
            return new ResponseEntity("User: " + securityUser.getName() + " has been registered.", HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity("Error: during saving; most likely duplicate data. " + e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
