package com.SpringBoot.RestfullApi.Dto;

import com.SpringBoot.RestfullApi.Dto.Enum.AuthProviderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    String username;
    String password;

    String email;

    private String providerId;

    @Enumerated(value = EnumType.STRING)
    private AuthProviderType providerType;
}
