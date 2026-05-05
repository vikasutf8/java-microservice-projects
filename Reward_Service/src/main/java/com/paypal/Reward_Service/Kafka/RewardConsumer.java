package com.paypal.Reward_Service.Kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paypal.Reward_Service.Enitity.Reward;
import com.paypal.Reward_Service.Enitity.Transaction;
import com.paypal.Reward_Service.Repository.RewardRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RewardConsumer {

    private  final RewardRepository rewardRepository;
    private  final ObjectMapper objectMapper;


    public RewardConsumer(RewardRepository rewardRepository, ObjectMapper objectMapper) {
        this.rewardRepository = rewardRepository;

//        setup objectmapper with JavaTime to handle localDateTime
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @KafkaListener(topics = "payment-initiated", groupId = "reward-group")
    public void consumerTransaction(Transaction transaction){
        try {
            if (rewardRepository.existsByTransactionId(transaction.getId())){
                System.out.println("Reward already exists for transaction"+transaction.getId());
                return;//skip process
            }
            Reward reward = Reward.builder()
                    .userId(transaction.getSenderId())
                    .points(transaction.getAmount() *100)
                    .sentAt(LocalDateTime.now())
                    .transactionId(transaction.getId())
                    .build();

            rewardRepository.save(reward);
            System.out.println("reward save successfully"+reward);

        } catch (Exception e) {
            System.out.println("error in reward saving "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
