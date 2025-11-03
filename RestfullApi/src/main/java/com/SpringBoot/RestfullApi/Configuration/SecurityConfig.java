package com.SpringBoot.RestfullApi.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/patient/**").hasAnyRole("ADMIN","DOCTOR","PATIENT")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/doctor/**").hasAnyRole("ADMIN","DOCTOR")

                )
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }


//    to handle role or user creata specifiic beans
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).build();
        UserDetails doctor = User.withUsername("doctor").password(passwordEncoder.encode("admin")).build();
        UserDetails patient = User.withUsername("patient").password(passwordEncoder.encode("admin")).build();

        return new InMemoryUserDetailsManager(admin,doctor,patient);


    }


}

// bydefualt its create all requests Protected
