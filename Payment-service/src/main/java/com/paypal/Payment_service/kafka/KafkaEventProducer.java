package com.paypal.Payment_service.kafka;

import java.util.concurrent.CompletableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paypal.Payment_service.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.kafka.support.SendResult;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {

    @Value("${kafka.topic.payment}")
    private String topic;

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendTransactionEvent(String key, Transaction transaction) {

        log.info("Producing event to topic {} with key {} and payload {}", topic, key, transaction);

        kafkaTemplate.send(topic, key, transaction)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        RecordMetadata metadata = result.getRecordMetadata();
                        log.info("✅ Sent → topic: {}, partition: {}, offset: {}",
                                metadata.topic(), metadata.partition(), metadata.offset());
                    } else {
                        log.error("❌ Failed to send event", ex);
                    }
                });
    }
}