package com.paypal.user_service.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String token;
    private String expireIn;


}

