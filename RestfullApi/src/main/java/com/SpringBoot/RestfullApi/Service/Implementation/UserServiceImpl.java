package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import com.SpringBoot.RestfullApi.Entity.User;
import com.SpringBoot.RestfullApi.Security.JwtUtil;
import com.SpringBoot.RestfullApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
//        here to validate user -- authenticate manager
//what its  internal; working -- as taking
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(),userRequestDto.getPassword())
        );

         //valid done
        User user =(User) authentication.getPrincipal();

        String token=jwtUtil.generateAccessToken(user);
        return new UserResponseDto(token, user.getId());
    }
}
