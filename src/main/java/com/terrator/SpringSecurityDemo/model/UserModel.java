package com.terrator.SpringSecurityDemo.model;

import lombok.Data;

import java.util.List;

@Data
public class UserModel {
    private String name;
    private String email;
    private String password;
    private List<String> roles;
}
