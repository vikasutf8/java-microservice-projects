package com.paypal.user_service.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.user_service.dto.JwtRequest;
import com.paypal.user_service.dto.LoginRequest;
import com.paypal.user_service.dto.SignupRequest;
import com.paypal.user_service.entity.User;
import com.paypal.user_service.repository.UserRepository;
import com.paypal.user_service.utils.JwtUtils;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private UserRepository userRepository;  //db operations
    // jwt utils for generating token
    private JwtUtils jwtUtils;
    private PasswordEncoder passwordEncoder;  // password encoding


    public AuthController(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {

        Optional<User> isUserExist = userRepository.findByEmail(signupRequest.getEmail());
        if (isUserExist.isPresent()) {
            return ResponseEntity.status(400).body("User already exist");
        }

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail()); 
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);

        

        return ResponseEntity.ok("User created successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            Optional<User> isUserExist = userRepository.findByEmail(loginRequest.getEmail());
            if (isUserExist.isEmpty()) {
                return ResponseEntity.status(401).body("User not found");
            }

            User user = isUserExist.get();
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Invalid password");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole());

            // generating token with claims
            String token = jwtUtils.generateToken(claims, user.getEmail());

            // System.out.println(token);
            return ResponseEntity.ok(new JwtRequest(token));

    }

}
