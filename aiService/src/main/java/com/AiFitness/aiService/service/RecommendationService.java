package com.AiFitness.aiService.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.AiFitness.aiService.model.Recommendation;
import com.AiFitness.aiService.repository.RecommendationRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Service
@Data
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;


    public List<Recommendation> getUserAllRecommendations(String userId) {
        // Implementation to fetch recommendations for the user
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendations(String activityId) {
        // Implementation to fetch recommendations for the activity
        return recommendationRepository.findByActivityId(activityId).orElseThrow(()-> new RuntimeException("Recommendation not found for activityId: "+activityId));
    }
}
