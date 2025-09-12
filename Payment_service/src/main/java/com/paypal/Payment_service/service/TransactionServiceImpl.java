package com.paypal.Payment_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.Payment_service.entity.Transaction;
import com.paypal.Payment_service.respository.TransactionRepo;



@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepo transactionRepo;
    private final ObjectMapper objectMapper;

    public TransactionServiceImpl(TransactionRepo transactionRepo, ObjectMapper objectMapper) {
        this.transactionRepo = transactionRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
       
        throw new UnsupportedOperationException("Unimplemented method 'saveTransaction'");
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
     
        Long senderId = transaction.getsenderId();
        Long receiverId = transaction.getreceiverId();

        Transaction newTransaction = new Transaction();
        newTransaction.setsenderId(senderId);
        newTransaction.setreceiverId(receiverId);
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setTimestamp(LocalDateTime.now());
        newTransaction.setStatus("SUCCESS");

        Transaction savedTransaction = transactionRepo.save(newTransaction);

        return savedTransaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
     
       return transactionRepo.findAll();
    }

    

}
