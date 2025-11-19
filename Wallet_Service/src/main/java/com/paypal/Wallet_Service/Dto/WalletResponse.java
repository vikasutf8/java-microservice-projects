package com.paypal.Wallet_Service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {


        private Long id;
        private Long userId;
        private String currency;
        private Long balance;
        private Long availableBalance;
        private LocalDateTime createdAt;

}
