package com.paypal.Payment_service.kafka;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paypal.Payment_service.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.kafka.support.SendResult;

@Component
@Data
public class KafkaEventProducer {

    private static final String TOPIC = "payment-initiated";

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    private final ObjectMapper objectMapper;

    // basic constructor injection
    @Autowired
    public KafkaEventProducer(KafkaTemplate<String, Transaction> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule((new JavaTimeModule()));
    }


    public void sendTransactionEvent(String key,Transaction transaction) {

        System.out.println( "Producing event to topic " + TOPIC + " with key: " + key + " and transaction: " + transaction);

        CompletableFuture<SendResult<String, Transaction>> future = kafkaTemplate.send(TOPIC, key, transaction);

        future.thenAccept(result -> {
            RecordMetadata metadata = result.getRecordMetadata();
            System.out.println("Event sent successfully with offset: " + metadata.topic() + "-" + metadata.partition() + "-" + metadata.offset());
        }).exceptionally(ex -> {
            System.err.println("Failed to send event: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        });
       

    }


}
