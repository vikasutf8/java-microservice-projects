package com.paypal.Payment_service.controller;

import java.util.List;

import com.paypal.Payment_service.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.Payment_service.entity.Transaction;
import com.paypal.Payment_service.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;

//    public TransactionController(TransactionService transactionService) {
//        this.transactionService = transactionService;
//    }

    @RequestMapping("/create")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction created = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Transaction>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Transaction created successfully")
                        .data(created)
                        .build()
        );
    }

    @RequestMapping("/all")
    public ResponseEntity<ApiResponse<List<Transaction>>> getAllTransactions() {

        return ResponseEntity.ok(
                ApiResponse.<List<Transaction>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Transactions retrieved successfully")
                        .data(transactionService.getAllTransactions())
                        .build()
        );
    }
}