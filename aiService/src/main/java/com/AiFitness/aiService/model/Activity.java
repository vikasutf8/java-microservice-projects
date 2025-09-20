package com.AiFitness.aiService.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;


import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Activity {
    
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
