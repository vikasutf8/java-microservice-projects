package com.AiFitness.activityService.service;


import org.springframework.stereotype.Service;

import com.AiFitness.activityService.Repository.ActivityRespository;
import com.AiFitness.activityService.dto.ActivityRequest;
import com.AiFitness.activityService.dto.ActivityResponse;
import com.AiFitness.activityService.models.Activity;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRespository activityRespository;
    private final UserValidationService userValidationService;

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
