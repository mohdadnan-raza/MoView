package com.moview.UserRegistrationService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moview.UserRegistrationService.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
	User findByUsernameOrEmailAndPassword(String username, String email, String password);
	Boolean existsByUsernameOrEmail(String username, String email);
    User findUserById(Long id);
}