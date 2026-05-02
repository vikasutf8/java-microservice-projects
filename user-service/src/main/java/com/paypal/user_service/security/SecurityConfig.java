package com.paypal.user_service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // disable CSRF
    // CSRF: Cross Site Request Forgery

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
        http
            .csrf(AbstractHttpConfigurer::disable) // 🔒 Disable CSRF for APIs (especially useful for stateless REST APIs)
            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/v1/user/**").permitAll() // ✅ Allow all requests to /api/v1/user/**
                .requestMatchers("/api/v1/auth/**").permitAll() // ✅ Allow all requests to /api/v1/auth/**
                .anyRequest().authenticated() // 🔐 Secure all other routes
            );

        return http.build();
    }

}
