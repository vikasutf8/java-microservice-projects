package com.SpringBoot.RestfullApi.Consumers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NewsConsumer implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {

        log.info("NewsConsumer: processing message...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String body = new String(message.getBody());
        String channel = new String(message.getChannel());

        log.info("[NEWS] Received on {} -> {}", channel, body);
    }
}

