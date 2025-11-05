package com.SpringBoot.RestfullApi.Controller;

import com.SpringBoot.RestfullApi.Dto.SignUpReqeustDto;
import com.SpringBoot.RestfullApi.Dto.SignupResponseDto;
import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import com.SpringBoot.RestfullApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

    @PostMapping("signUp")
    public ResponseEntity<SignupResponseDto> signUpUser(@RequestBody SignUpReqeustDto signUpReqeustDto){
        return ResponseEntity.ok(userService.signup(signUpReqeustDto));
    }
}
