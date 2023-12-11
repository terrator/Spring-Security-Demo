package com.terrator.SpringSecurityDemo.model;

import com.terrator.SpringSecurityDemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String name;
    private String email;
    private String password;
//    private List<String> roles;
//
    private List<Role> roles;
}
