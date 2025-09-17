package com.AiFitness.activityService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserValidationService {
    private final WebClient userServiceWebClient;


    public Boolean validateUser(String userId) {
        try {
            return userServiceWebClient.get().uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        } catch (WebClientResponseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
