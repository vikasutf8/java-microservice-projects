package com.paypal.Payment_service.dto.WalletDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditRequest {

    private  Long userId;

    private String currency;

    private Long amount;
}
