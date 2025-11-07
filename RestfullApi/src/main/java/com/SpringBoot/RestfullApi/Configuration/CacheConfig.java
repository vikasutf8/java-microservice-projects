package com.SpringBoot.RestfullApi.Configuration;


import com.SpringBoot.RestfullApi.Consumers.FoodConsumer;
import com.SpringBoot.RestfullApi.Consumers.GenericaConsumer;
import com.SpringBoot.RestfullApi.Consumers.NewsConsumer;
import com.SpringBoot.RestfullApi.Consumers.RedisConsumer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;

@Configuration
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(180)); // default TTL for all caches

        return RedisCacheManager
                .builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public RedisMessageListenerContainer redisPubSub(RedisConnectionFactory redisConnectionFactory, RedisConsumer redisConsumer, NewsConsumer newsConsumer, FoodConsumer foodConsumer,GenericaConsumer genericaConsumer){

        RedisMessageListenerContainer container =new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
//        container.addMessageListener(redisConsumer,new ChannelTopic("notification"));
//        container.addMessageListener(newsConsumer,new ChannelTopic("news"));
//        container.addMessageListener(foodConsumer,new ChannelTopic("food"));
            container.addMessageListener(genericaConsumer,new PatternTopic("gen*"));



        return container;
    }
}
