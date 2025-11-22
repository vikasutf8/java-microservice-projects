package com.paypal.Payment_service.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.Payment_service.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {


}
