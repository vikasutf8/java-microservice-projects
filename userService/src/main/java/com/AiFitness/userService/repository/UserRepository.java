package com.AiFitness.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AiFitness.userService.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // custom method to find user by email
    boolean existsByEmail(String email);
}
