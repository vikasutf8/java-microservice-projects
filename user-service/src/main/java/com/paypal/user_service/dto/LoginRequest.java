package com.paypal.user_service.dto;


// dto -- data comming from client to server and stored as object here ...this object will be used in controller

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

        private String email;
        private String password;


}
