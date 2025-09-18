package com.paypal.Payment_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.Payment_service.entity.Transaction;
import com.paypal.Payment_service.kafka.KafkaEventProducer;
import com.paypal.Payment_service.respository.TransactionRepo;

import lombok.AllArgsConstructor;



@Service

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepo transactionRepo;
    private final ObjectMapper objectMapper;
    private final KafkaEventProducer kafkaEventProducer;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionServiceImpl(TransactionRepo transactionRepo, ObjectMapper objectMapper, KafkaEventProducer kafkaEventProducer) {
        this.transactionRepo = transactionRepo;
        this.objectMapper = objectMapper;
        this.kafkaEventProducer = kafkaEventProducer;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
       
        throw new UnsupportedOperationException("Unimplemented method 'saveTransaction'");
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
     
        Long senderId = transaction.getsenderId();
        Long receiverId = transaction.getreceiverId();
        Double amount = transaction.getAmount();

        Transaction newTransaction = new Transaction();
        newTransaction.setsenderId(senderId);
        newTransaction.setreceiverId(receiverId);
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setTimestamp(LocalDateTime.now());
        newTransaction.setStatus("SUCCESS");

        Transaction savedTransaction = transactionRepo.save(newTransaction);

        try {
            String eventPayload = objectMapper.writeValueAsString(savedTransaction);
            String key = String.valueOf(savedTransaction.getId());
            kafkaEventProducer.sendTransactionEvent(key,savedTransaction);

            System.out.println("Transaction event produced: " + eventPayload);
        } catch (Exception e) {
            System.err.println("Failed to produce transaction event: " + e.getMessage());
            e.printStackTrace();
        }


        return savedTransaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
     
       return transactionRepo.findAll();
    }

    

}
