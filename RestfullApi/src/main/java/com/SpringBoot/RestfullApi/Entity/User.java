package com.SpringBoot.RestfullApi.Entity;

import com.SpringBoot.RestfullApi.Dto.Enum.AuthProviderType;
import com.SpringBoot.RestfullApi.Entity.Enum.UserRoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User  implements UserDetails , Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "username",unique = true)
    private String username;

    private String password;

    private String email;

    private String providerId;

    private AuthProviderType providerType;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<UserRoleType> userRole =new HashSet<>();

//    each user can be patient



    //    this function for authorization not for authenticated
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public User orElseThrow(Object userNotFound) {
        return null;
    }
}


//here i want that use daoProvider and its done by userDetails