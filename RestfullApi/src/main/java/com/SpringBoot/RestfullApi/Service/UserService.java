package com.SpringBoot.RestfullApi.Service;

import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> handleOauth2LoginRequest(OAuth2User oAuth2User, String registrationId);
}
