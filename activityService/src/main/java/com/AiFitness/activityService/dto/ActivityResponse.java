package com.AiFitness.activityService.dto;

import java.time.LocalDateTime;
import java.util.Map;


import com.AiFitness.activityService.models.ActivityType;

import lombok.Data;

@Data
public class ActivityResponse {

    private String id;
    private String userId;
    private ActivityType activityType;
    private Integer caloriesBurned;
    private Integer duration;
    private LocalDateTime startTime;   
    private Map<String, Object> additionalMatrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
