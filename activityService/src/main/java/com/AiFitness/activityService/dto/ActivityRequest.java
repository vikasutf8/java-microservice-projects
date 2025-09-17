package com.AiFitness.activityService.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.AiFitness.activityService.models.ActivityType;

import lombok.Data;

@Data
public class ActivityRequest {
    
    private String userId;
    private ActivityType activityType;
    private Integer duration; 
    private Integer caloriesBurned;
    private LocalDateTime startTime; 
    private Map<String, Object> additionalMatrics;
}
