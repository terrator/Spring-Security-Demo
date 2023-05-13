package com.terrator.SpringSecurityDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Entity(name = "users")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "authority_id", referencedColumnName = "id")
//    private Set<Authority> authorities = new HashSet<>();
    private List<String> roles;
}
