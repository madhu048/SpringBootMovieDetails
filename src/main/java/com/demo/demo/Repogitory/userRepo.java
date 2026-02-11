package com.demo.demo.Repogitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.Entity.User;

public interface userRepo extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
