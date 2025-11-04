package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Dto.Enum.AuthProviderType;
import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import com.SpringBoot.RestfullApi.Entity.User;
import com.SpringBoot.RestfullApi.Repository.UserRepository;
import com.SpringBoot.RestfullApi.Security.JwtUtil;
import com.SpringBoot.RestfullApi.Security.OAuthUtil;
import com.SpringBoot.RestfullApi.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OAuthUtil oAuthUtil;

    @Autowired
    private UserRepository userRepository;

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

    public User signUpInternal(User userRequestDto){
        User user = userRepository.findByUsername(userRequestDto.getUsername());

        return userRepository.save(User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .build());
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponseDto> handleOauth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
//        provider type and provider id
        AuthProviderType providerType = oAuthUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = oAuthUtil.determineProviderTypeFormOAuth2User(oAuth2User,registrationId);


//        save it info with user

        User user =userRepository.findByProviderIdAndProviderType(providerId, providerType);

        String email =oAuth2User.getAttribute("email");
        User emailUser =userRepository.findByUsername(email);

        if(user ==null || emailUser ==null){
//            signup flows:
            String username =oAuthUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId,providerId);
//            user = signUpInternal(new UserRequestDto(username, null))
        }
        else if (user !=null){
            //if user having acocunt directly login
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }else{
            throw new BadCredentialsException("this email is already registered with provider"+email);
        }


//        now login
        UserResponseDto userResponseDto=new UserResponseDto(jwtUtil.generateAccessToken(signUpInternal(user)),user.getId() );


        return  ResponseEntity.ok(userResponseDto);
        //else singup then login
    }
}
