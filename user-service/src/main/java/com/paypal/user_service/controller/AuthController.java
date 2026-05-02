package com.paypal.user_service.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.paypal.user_service.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;  //db operations
    // jwt utils for generating token
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;  // password encoding


//    public AuthController(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.jwtUtils = jwtUtils;
//        this.passwordEncoder = passwordEncoder;
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {

        Optional<User> isUserExist = userRepository.findByEmail(signupRequest.getEmail());
        if (isUserExist.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(
                            ApiResponse.builder()
                            .status(HttpStatus.NOT_ACCEPTABLE.value())
                            .message("User already exists with this email")
                                    .data(signupRequest)
                            .build()
                    );
        }

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail()); 
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);

//        return ResponseEntity.ok("User registered successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.builder()
                                .status(HttpStatus.CREATED.value())
                                .message("User registered successfully")
                                .data(user) // or send user DTO if needed
                                .build()
                );
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            Optional<User> isUserExist = userRepository.findByEmail(loginRequest.getEmail());
            if (isUserExist.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponse.builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .message("User not found with this email")
                                .data(loginRequest) // or send user DTO if needed
                                .build()
                );
            }

            User user = isUserExist.get();
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        ApiResponse.builder()
                                .status(HttpStatus.UNAUTHORIZED.value())
                                .message("Invalid password")
                                .data(loginRequest) // or send user DTO if needed
                                .build()
                );
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole());
            System.out.println(claims.get("role"));
            // generating token with claims
            String token = jwtUtils.generateToken(claims, user.getEmail());

            // System.out.println(token);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ApiResponse.builder()
                                    .status(HttpStatus.OK.value())
                                    .message("Login successful")
                                    .data(new JwtRequest(token,"1000 * 60 * 60 * 24")) // or send user DTO if needed
                                    .build()
                    );

    }

}
