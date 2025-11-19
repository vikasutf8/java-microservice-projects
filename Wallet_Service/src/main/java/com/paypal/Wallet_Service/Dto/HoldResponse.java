package com.paypal.Wallet_Service.Dto;


import com.paypal.Wallet_Service.Enitity.Enum.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoldResponse  {

    private Long id;
    private Long walletId;
    private String holdReference;
    private Long amount;
    private WalletStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime expireAt;
}
