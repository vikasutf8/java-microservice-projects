package com.paypal.user_service.Client;


import com.paypal.user_service.dto.CreateWalletClient;
import com.paypal.user_service.dto.WalletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


//@FeignClient(name='Wallet_Service', url ="http://localhost:8086")
public interface WalletClient {

    @PostMapping()
    WalletResponse createWallet(@RequestBody CreateWalletClient client);
}
