package com.paypal.Payment_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
@EnableWebSecurity
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
            .csrf(csrf -> csrf.disable()) // 🔒 Disable CSRF for APIs (especially useful for stateless REST APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("api/v1/transaction/**").permitAll() // ✅ Allow all requests to /api/v1/user/**
                .anyRequest().authenticated() // 🔐 Secure all other routes
            );

        return http.build();
    }

}

