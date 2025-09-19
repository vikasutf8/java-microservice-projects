package com.AiFitness.aiService.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
public class GeminiService {

    private final WebClient webClient;

    @Value("${url}")
    private String  geminiApiUrl ;
    @Value("${key}")
    private String geminiApiKey;


    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    public String getRecommendations(String userInput) {
        Map<String, Object> requestBody = Map.of("contents",  new Object[]{
            Map.of("parts", new Object[]{
                Map.of("text", userInput)
            })
        });

        String response = webClient.post()
                .uri(geminiApiUrl)
                .header("Content-Type", "application/json")
                .header("X-google-api-key", geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }
   
}
