package com.SpringBoot.RestfullApi.Service;

import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
}
