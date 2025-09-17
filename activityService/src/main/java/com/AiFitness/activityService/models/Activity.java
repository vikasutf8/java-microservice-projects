package com.AiFitness.activityService.models;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "activities")
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

    @Field("matrics")
    private Map<String, Object> additionalMatrics;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


}
