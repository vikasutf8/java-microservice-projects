package com.SpringBoot.RestfullApi.Consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class GenericaConsumer implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("pattern"+new String(pattern));
        log.info("channel"+new String(message.getChannel()));
        log.info("body"+new String(message.getBody()));

    }
}
