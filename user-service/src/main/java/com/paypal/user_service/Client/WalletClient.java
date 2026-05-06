package com.paypal.user_service.Client;


import com.paypal.user_service.dto.CreateWalletClient;
import com.paypal.user_service.dto.WalletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "Wallet_Service ",
        url = "http://localhost:8086/api/v1/wallets" // <-- put your URL in application.properties
)
public interface WalletClient {

    @PostMapping("/api/v1/wallets")
    WalletResponse createWallet(@RequestBody CreateWalletClient request);
}
