package com.paypal.Wallet_Service.Repository;

import com.paypal.Wallet_Service.Enitity.Wallet;
import com.paypal.Wallet_Service.Enitity.WalletHold;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserId(Long UserId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findByUserIdAndCurrency(Long UserId , String currency);
}
