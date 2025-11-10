package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Dto.Enum.AuthProviderType;
import com.SpringBoot.RestfullApi.Dto.SignUpReqeustDto;
import com.SpringBoot.RestfullApi.Dto.SignupResponseDto;
import com.SpringBoot.RestfullApi.Dto.UserRequestDto;
import com.SpringBoot.RestfullApi.Dto.UserResponseDto;
import com.SpringBoot.RestfullApi.Entity.Enum.UserRoleType;
import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Entity.User;
import com.SpringBoot.RestfullApi.Repository.PatientRepository;
import com.SpringBoot.RestfullApi.Repository.UserRepository;
import com.SpringBoot.RestfullApi.Security.JwtUtil;
import com.SpringBoot.RestfullApi.Security.OAuthUtil;
import com.SpringBoot.RestfullApi.Service.UserService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OAuthUtil oAuthUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto, HttpSession session) {
//        here to validate user -- authenticate manager
//what its  internal; working -- as taking
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(),userRequestDto.getPassword())
        );

         //valid done
        User user =(User) authentication.getPrincipal();

        String token=jwtUtil.generateAccessToken(user);
        return new UserResponseDto(token, user.getId());


//         seesion



    }

    public User signUpInternal(SignUpReqeustDto signupRequestDto, AuthProviderType authProviderType, String providerId) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername());
        if (user != null)
            throw new IllegalArgumentException("User already exists");

        user = User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
//                .roles(Set.of(UserRoleType.PATIENT)) // bydefual all  entry are patient
                .userRole(signupRequestDto.getUserRole())
                .build();

        if (authProviderType == AuthProviderType.EMAIL) {
            user.setPassword(passwordEncoder().encode(signupRequestDto.getPassword()));
        }

        user =userRepository.save(user);

        Patient patient =Patient.builder()
                .name(signupRequestDto.getUsername())
                .email(signupRequestDto.getName())
                .user(user)
                .build();
        patientRepository.save(patient);

        return user;
    }

    public SignupResponseDto signup(SignUpReqeustDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto, AuthProviderType.EMAIL, null);
        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponseDto> handleOauth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
//        provider type and provider id
//        return null;
        AuthProviderType providerType = oAuthUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = oAuthUtil.determineProviderTypeFormOAuth2User(oAuth2User,registrationId);
        String name =oAuth2User.getAttribute("name");

//        save it info with user

        User user =userRepository.findByProviderIdAndProviderType(providerId, providerType);

        String email =oAuth2User.getAttribute("email");
        User emailUser =userRepository.findByUsername(email);

        if(user ==null || emailUser ==null){
//            signup flows:
            String username =oAuthUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId,providerId);
          user =signUpInternal(new SignUpReqeustDto(username,null,name,Set.of(UserRoleType.PATIENT)),providerType,providerId);
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



    public void login(String email, String password, HttpSession session) throws IOException {
        // find user ignoring case
        User user = userRepository.findByEmailIgnoreCase(email);

        // validate password (replace with hashed comparison in real apps)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        // store in session
        session.setAttribute("loggedUser", user);

        // OR store only id / email
        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getEmail());
    }

//    @Override
//    public void getProfile(HttpSession session) {
//        Object email =session.getAttribute("email");
//        Object userId =session.getAttribute("userId");
//    }

    @Override
    public void getProfile(HttpSession session) {

        if (session ==null)return;
        Object email =session.getAttribute("email");
        Object userId =session.getAttribute("userId");
    }


}
