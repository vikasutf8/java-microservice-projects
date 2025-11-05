package com.SpringBoot.RestfullApi.Dto;

import com.SpringBoot.RestfullApi.Entity.Enum.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReqeustDto {

    private String username;
    private String password;
    private String name;

    Set<UserRoleType> userRole =new HashSet<>();
}
