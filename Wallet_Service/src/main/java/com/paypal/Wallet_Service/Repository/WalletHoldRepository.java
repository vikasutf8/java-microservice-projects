package com.paypal.Wallet_Service.Repository;

import com.paypal.Wallet_Service.Enitity.Enum.WalletStatus;
import com.paypal.Wallet_Service.Enitity.Wallet;
import com.paypal.Wallet_Service.Enitity.WalletHold;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WalletHoldRepository extends JpaRepository<WalletHold, Long> {

    Optional<WalletHold> findByHoldReference(String holdReference);

    List<WalletHold> findByStatusAndExpireAtBefore(WalletStatus walletStatus, LocalDateTime now);
}
