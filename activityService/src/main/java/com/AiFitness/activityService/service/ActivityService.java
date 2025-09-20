package com.AiFitness.activityService.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.AiFitness.activityService.Repository.ActivityRespository;
import com.AiFitness.activityService.dto.ActivityRequest;
import com.AiFitness.activityService.dto.ActivityResponse;
import com.AiFitness.activityService.models.Activity;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ObjectMapper objectMapper;
    private final ActivityRespository activityRespository;
    private final UserValidationService userValidationService;
    private final KafkaTemplate<String, Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName ;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        Boolean userExists = userValidationService.validateUser(activityRequest.getUserId());
        if (!userExists) {
            throw new RuntimeException("User not found");
        }
        // Logic to track activity
       Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .activityType(activityRequest.getActivityType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMatrics(activityRequest.getAdditionalMatrics())
                .build();

        Activity savedActivity = activityRespository.save(activity);

       try {
        // String jsons = objectMapper.writeValueAsString(savedActivity);
        kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
         log.info("Activity sent to Kafka topic: {}", topicName);
       } catch (Exception e) {
        e.printStackTrace();
       }

        return MapToResponse(savedActivity);

    }

    private ActivityResponse MapToResponse(Activity activity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setActivityType(activity.getActivityType());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setAdditionalMatrics(activity.getAdditionalMatrics());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        return activityResponse;
    }
}
