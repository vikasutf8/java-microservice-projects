package com.paypal.Wallet_Service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebitResponse  {
    private Long userId;
    private Long amount;
    private String currency;

}
