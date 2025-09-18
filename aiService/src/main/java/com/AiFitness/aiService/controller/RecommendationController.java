package com.AiFitness.aiService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AiFitness.aiService.model.Recommendation;
import com.AiFitness.aiService.service.RecommendationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationController {


    private final RecommendationService recommendationService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserAllRecommendation(@PathVariable String userId) {
        // Implementation to fetch recommendations for the user
        return ResponseEntity.ok().body(recommendationService.getUserAllRecommendations(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendations(@PathVariable String activityId) {
        // Implementation to fetch recommendations for the activity
        return ResponseEntity.ok().body(recommendationService.getActivityRecommendations(activityId));
    }
}
