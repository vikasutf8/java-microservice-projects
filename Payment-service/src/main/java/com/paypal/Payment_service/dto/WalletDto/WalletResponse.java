package com.paypal.Payment_service.dto.WalletDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
