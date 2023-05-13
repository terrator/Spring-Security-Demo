package com.terrator.SpringSecurityDemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//@Entity(name = "authorities")
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class Authority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;
}
