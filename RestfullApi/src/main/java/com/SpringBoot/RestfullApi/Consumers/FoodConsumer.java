package com.SpringBoot.RestfullApi.Consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FoodConsumer implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {

        log.info("FoodConsumer: processing message...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String body = new String(message.getBody());
        String channel = new String(message.getChannel());

        log.info("[FOOD] Received on {} -> {}", channel, body);
    }
}
