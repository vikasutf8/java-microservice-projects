package com.SpringBoot.RestfullApi.Controller;

import com.SpringBoot.RestfullApi.Dto.SignUpReqeustDto;
import com.SpringBoot.RestfullApi.Dto.SignupResponseDto;
import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import com.SpringBoot.RestfullApi.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto, HttpSession session){
        return ResponseEntity.ok(userService.createUser(userRequestDto,session));
    }

    @PostMapping("signUp")
    public ResponseEntity<SignupResponseDto> signUpUser(@RequestBody SignUpReqeustDto signUpReqeustDto){
        return ResponseEntity.ok(userService.signup(signUpReqeustDto));
    }

    @GetMapping("logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

    @GetMapping("me")
    public  void profile(HttpServletRequest session){
        userService.getProfile(session.getSession(false));
    }




}
