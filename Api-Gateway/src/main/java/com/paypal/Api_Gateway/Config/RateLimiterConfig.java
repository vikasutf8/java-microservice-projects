package com.paypal.Api_Gateway.Config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RateLimiterConfig {

    @Bean
    public KeyResolver jwtKeyResolver() {
        return exchange -> {
            String auth = exchange.getRequest().getHeaders().getFirst("X-User-Id");
            if(auth != null){
                return Mono.just(auth);
            }
            //fallback on Ip address
            return  Mono.just(
                    Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress()
            );
        };
    }

}
