package com.AiFitness.userService.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AiFitness.userService.dto.RegisterRequest;
import com.AiFitness.userService.dto.UserResponse;
import com.AiFitness.userService.services.UserServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest ) {
        // Implement user registration logic here
        return ResponseEntity.ok(userServices.register(registerRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        // Implement user profile retrieval logic here
        return ResponseEntity.ok(userServices.getUserProfile(userId));
       
       
    }

    @GetMapping("/allUser")
    public List<UserResponse> getAllUsers() {
        // Implement user profile retrieval logic here
        return userServices.getAllUsers();
       
       
    }
}
