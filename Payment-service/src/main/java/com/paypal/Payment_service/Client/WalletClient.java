//package com.paypal.Payment_service.Client;
//
//
//@FeignClient(
//        name = "wallet-service",
//        url = "${wallet.service.url}" // <-- put your URL in application.properties
//)
//public interface WalletClient {
//
//    @PostMapping("/api/v1/wallets")
//    WalletResponse createWallet(@RequestBody CreateWalletRequest request);
//
//    @PostMapping("/api/v1/wallets/credit")
//    WalletResponse credit(@RequestBody CreditRequest request);
//
//    @PostMapping("/api/v1/wallets/debit")
//    WalletResponse debit(@RequestBody DebitRequest request);
//
//    @GetMapping("/api/v1/wallets/{userId}")
//    WalletResponse getWallet(@PathVariable("userId") Long userId);
//
//    @PostMapping("/api/v1/wallets/holds")
//    HoldResponse placeHold(@RequestBody HoldRequest request);
//
//    @PostMapping("/api/v1/wallets/holds/capture")
//    WalletResponse captureHold(@RequestBody CaptureRequest request);
//
//    @PostMapping("/api/v1/wallets/holds/release/{holdReference}")
//    HoldResponse releaseHold(@PathVariable("holdReference") String holdReference);
//}
//
