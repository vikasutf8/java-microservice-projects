package com.SpringBoot.RestfullApi.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UtilConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //    to handle role or user creata specifiic beans
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).build();
//        UserDetails doctor = User.withUsername("doctor").password(passwordEncoder.encode("admin")).build();
//        UserDetails patient = User.withUsername("patient").password(passwordEncoder.encode("admin")).build();
//
//        return new InMemoryUserDetailsManager(admin,doctor,patient);
//    }
}
