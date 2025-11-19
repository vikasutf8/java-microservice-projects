package com.paypal.Wallet_Service.Controller;


import com.paypal.Wallet_Service.Dto.*;
import com.paypal.Wallet_Service.Service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    // ------------------------------------------
    // 1. Create Wallet
    // POST /api/v1/wallets
    // ------------------------------------------
    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@RequestBody CreateWalletRequest request) {
        WalletResponse response = walletService.createWallet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ------------------------------------------
    // 2. Credit
    // POST /api/v1/wallets/credit
    // ------------------------------------------
    @PostMapping("/credit")
    public ResponseEntity<WalletResponse> credit(@RequestBody CreditResponse request) {
        WalletResponse response = walletService.credit(request);
        return ResponseEntity.ok(response);
    }

    // ------------------------------------------
    // 3. Debit
    // POST /api/v1/wallets/debit
    // ------------------------------------------
    @PostMapping("/debit")
    public ResponseEntity<WalletResponse> debit(@RequestBody DebitResponse request) {
        WalletResponse response = walletService.debit(request);
        return ResponseEntity.ok(response);
    }

    // ------------------------------------------
    // 4. Get Wallet by User ID
    // GET /api/v1/wallets/{userId}
    // ------------------------------------------
    @GetMapping("/{userId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable Long userId) {
        WalletResponse response = walletService.getWallet(userId);
        return ResponseEntity.ok(response);
    }

    // ------------------------------------------
    // 5. Place Hold
    // POST /api/v1/wallets/hold
    // ------------------------------------------
    @PostMapping("/hold")
    public ResponseEntity<HoldResponse> placeHold(@RequestBody HoldRequest request) {
        HoldResponse response = walletService.placeHold(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ------------------------------------------
    // 6. Capture Hold
    // POST /api/v1/wallets/hold/capture
    // ------------------------------------------
    @PostMapping("/hold/capture")
    public ResponseEntity<WalletResponse> captureHold(@RequestBody CaptureRequest request) {
        WalletResponse response = walletService.captureHold(request);
        return ResponseEntity.ok(response);
    }

    // ------------------------------------------
    // 7. Release Hold
    // DELETE /api/v1/wallets/hold/{holdReference}
    // ------------------------------------------
    @PostMapping("/hold/{holdReference}")
    public ResponseEntity<HoldResponse> releaseHold(@PathVariable String holdReference) {
        HoldResponse response = walletService.releaseHold(holdReference);
        return ResponseEntity.ok(response);
    }
}

