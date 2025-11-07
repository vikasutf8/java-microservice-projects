package com.SpringBoot.RestfullApi.Consumers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisConsumer implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("thread sleep");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String body =new String(message.getBody());
        String channel =new String(message.getChannel());
        log.info("Message received: {} and {}", body, channel);

    }
}
