package com.paypal.user_service.controller;

import java.util.List;

import com.paypal.user_service.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.user_service.entity.User;
import com.paypal.user_service.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<User>builder()
                                .status(HttpStatus.CREATED.value())
                                .message("User created successfully")
                                .data(userService.creataUser(user))
                                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<User>builder()
                        .status(HttpStatus.OK.value())
                        .message("User fetched successfully")
                        .data(userService.getUserById(id).orElse(null))
                        .build()
        );
    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("user all list");

        return ResponseEntity.ok(userService.findAllUsers());
    }
}
