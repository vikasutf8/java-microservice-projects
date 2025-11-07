package com.SpringBoot.RestfullApi.Producers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class RedisPublisher {

    private  final StringRedisTemplate redisTemplate;

    @PostMapping
    public  void publish(@RequestParam String message){
        redisTemplate.convertAndSend("notification","this is redis publisher");
        redisTemplate.convertAndSend("gen-news","this is news publisher");
        redisTemplate.convertAndSend("gen-food","this is food publisher");

    }
}
