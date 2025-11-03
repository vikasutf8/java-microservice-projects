package com.SpringBoot.RestfullApi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "username",unique = true)
    private String username;

    private String password;

//    this function for authorization not for authenticated
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}


//here i want that use daoProvider and its done by userDetails