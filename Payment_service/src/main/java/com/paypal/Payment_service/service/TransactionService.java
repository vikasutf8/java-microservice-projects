package com.paypal.Payment_service.service;

import java.util.List;

import com.paypal.Payment_service.entity.Transaction;


public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
    Transaction createTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
}
