package com.sasmitha.lms.repository;

import com.sasmitha.lms.model.User;
import com.sasmitha.lms.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
    Optional<User> findByEmail(String email);
}
