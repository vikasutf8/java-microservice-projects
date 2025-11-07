package com.SpringBoot.RestfullApi.Service;

import com.SpringBoot.RestfullApi.Dto.SignUpReqeustDto;
import com.SpringBoot.RestfullApi.Dto.SignupResponseDto;
import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto, HttpSession session);

    ResponseEntity<UserResponseDto> handleOauth2LoginRequest(OAuth2User oAuth2User, String registrationId);


    SignupResponseDto signup(SignUpReqeustDto signUpReqeustDto);

    void login(String email, String password, HttpSession session);


    void getProfile(HttpSession session);
}
