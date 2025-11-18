package com.paypal.Api_Gateway.Filters;

import com.paypal.Api_Gateway.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilters implements GlobalFilter, Ordered {

    private static final List<String> PUBLIC_PATHS = List.of(
            "/auth/signup",
            "/auth/login"
    );

    @Override
    public int getOrder() {
        return -100;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path =exchange.getRequest().getPath().value();
        String normalizedPath =path.replaceAll("/", "");


        if (PUBLIC_PATHS.contains(normalizedPath)){
            return chain.filter(exchange)
                    .doOnSubscribe(s-> System.out.println("processing without check"))
                    .doOnSuccess(v-> System.out.println("successing path"))
                    .doOnError(e-> System.out.println("error occured"));
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            String token = authHeader.substring(7);
            Claims claims = JwtUtils. validateToken(token);
            exchange.getRequest().mutate()
                    .header("X-User-Email",claims.getSubject())
                    .header("X-User-Id", claims.get("userId", String.class))
                    .header("X-User-Role",claims.get("role", String.class))
                    .build();

            return chain.filter(exchange)
                    .doOnSubscribe(s-> System.out.println("processing without check"))
                    .doOnSuccess(v-> System.out.println("successing path"))
                    .doOnError(e-> System.out.println("error occured"));


        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

    }
}
