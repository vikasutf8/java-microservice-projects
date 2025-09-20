package com.AiFitness.aiService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.AiFitness.aiService.model.Activity;
import com.AiFitness.aiService.model.Recommendation;
import com.AiFitness.aiService.repository.RecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class ActivityMessageListener {

    @Autowired
    private final ActivityAiService activityAiService;

    @Autowired
    private final RecommendationService recommendationService;

    @Autowired
    private final RecommendationRepository recommendationRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.name}" , groupId = "activity-processing-group")
    public void processActivity(String jsons) {
     try {
      Activity activity = objectMapper.readValue(jsons, Activity.class);
      Recommendation recommendation = activityAiService.generateRecommendations(activity);
      log.info("Recommendation: {}", recommendation);
      recommendationRepository.save(recommendation);

     } catch (Exception e) {
        log.error("Error processing activity", e);
        e.printStackTrace();
     }
    }


}
