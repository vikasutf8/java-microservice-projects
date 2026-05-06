package com.paypal.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {


    private Long id;
    private Long userId;
    private String currency;
    private Long balance;
    private Long availableBalance;
}
