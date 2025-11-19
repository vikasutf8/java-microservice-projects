package com.paypal.Wallet_Service.Scheduler;


import com.paypal.Wallet_Service.Enitity.Enum.WalletStatus;
import com.paypal.Wallet_Service.Enitity.Wallet;
import com.paypal.Wallet_Service.Enitity.WalletHold;
import com.paypal.Wallet_Service.Repository.WalletHoldRepository;
import com.paypal.Wallet_Service.Repository.WalletRepository;
import com.paypal.Wallet_Service.Service.WalletService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class HoldExpiryScheduler {

    private final WalletHoldRepository walletHoldRepository;
    private final WalletRepository walletRepository;
    private final WalletService walletService;


    @Scheduled(fixedRateString = "${wallet.hold.expiry.scan-rate-ms:60000}")
    public void expireHeldTransactions() {

        LocalDateTime now = LocalDateTime.now();

        // Fetch all expired holds with status ACTIVE
        List<WalletHold> expiredHolds =
                walletHoldRepository.findByStatusAndExpireAtBefore(WalletStatus.ACTIVE, now);

        for (WalletHold hold : expiredHolds) {
            String ref = hold.getHoldReference();
            try {
                // Reuse existing hold release logic
                walletService.releaseHold(ref);
                System.out.println("✓ Expired hold released: " + ref);
            } catch (Exception ex) {
                System.err.println("✗ Failed to release expired hold: " + ref + " | " + ex.getMessage());
            }
        }
    }

}
