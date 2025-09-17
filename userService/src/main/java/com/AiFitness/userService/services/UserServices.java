package com.AiFitness.userService.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AiFitness.userService.dto.RegisterRequest;
import com.AiFitness.userService.dto.UserResponse;
import com.AiFitness.userService.models.User;
import com.AiFitness.userService.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServices {
    @Autowired
    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {

        // validation
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        // create user and store coming dto data into it>
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        User savedUser = userRepository.save(user);
        System.out.println(savedUser + " saved successfully");

        // convert saved user to UserResponse
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId().toString());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setCreatedAt(savedUser.getCreatedAt().toString());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt().toString());

        System.out.println(userResponse);
        return userResponse;

    }

    public UserResponse getUserProfile(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // convert user to UserResponse
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId().toString());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(null);
        userResponse.setCreatedAt(user.getCreatedAt().toString());
        userResponse.setUpdatedAt(user.getUpdatedAt().toString());

        return userResponse;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList();
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId().toString());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setEmail(user.getEmail());
            userResponse.setPassword(null);
            userResponse.setCreatedAt(user.getCreatedAt().toString());
            userResponse.setUpdatedAt(user.getUpdatedAt().toString());
            userResponses.add(userResponse);
        }
        return userResponses;
    }


    public Boolean existsByUserId(String userId) {
        log.info("Checking if user with ID User Service", userId);
        return userRepository.existsById(userId);
    }

}
