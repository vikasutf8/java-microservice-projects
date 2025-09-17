package com.AiFitness.activityService.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AiFitness.activityService.dto.ActivityRequest;
import com.AiFitness.activityService.dto.ActivityResponse;
import com.AiFitness.activityService.service.ActivityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
        // Logic to handle activity tracking
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));

    }

}
