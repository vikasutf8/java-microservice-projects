package com.paypal.Payment_service.Client;


import com.paypal.Payment_service.dto.WalletDto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "Wallet_Service ",
        url = "http://localhost:8086/api/v1/wallets" // <-- put your URL in application.properties
)
public interface WalletClient {

    @PostMapping("/api/v1/wallets")
    WalletResponse createWallet(@RequestBody CreateWalletRequest request);

    @PostMapping("/api/v1/wallets/credit")
    WalletResponse credit(@RequestBody CreditRequest request);

    @PostMapping("/api/v1/wallets/debit")
    WalletResponse debit(@RequestBody DebitRequest request);

    @GetMapping("/api/v1/wallets/{userId}")
    WalletResponse getWallet(@PathVariable("userId") Long userId);

    @PostMapping("/api/v1/wallets/holds")
    HoldResponse placeHold(@RequestBody HoldRequest request);

    @PostMapping("/api/v1/wallets/holds/capture")
    WalletResponse captureHold(@RequestBody CaptureRequestDto  request);

    @PostMapping("/api/v1/wallets/holds/release/{holdReference}")
    HoldResponse releaseHold(@PathVariable("holdReference") String holdReference);
}

