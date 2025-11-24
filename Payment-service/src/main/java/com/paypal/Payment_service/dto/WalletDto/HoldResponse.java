package com.paypal.Payment_service.dto.WalletDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoldResponse {

    private String holdReference;

    private Long amount;

    private String status;
}
