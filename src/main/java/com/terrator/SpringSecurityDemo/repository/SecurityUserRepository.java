package com.terrator.SpringSecurityDemo.repository;

import com.terrator.SpringSecurityDemo.entity.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByEmail(String email);
    Optional<SecurityUser> findByName(String  userName);
}
