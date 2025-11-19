package com.paypal.Wallet_Service.Repository;

import com.paypal.Wallet_Service.Enitity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction ,Long> {
}
