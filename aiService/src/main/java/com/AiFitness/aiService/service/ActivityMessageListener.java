package com.AiFitness.aiService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.AiFitness.aiService.model.Activity;

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

    @KafkaListener(topics = "${kafka.topic.name}" , groupId = "activity-processing-group")
    public void processActivity(Activity activity) {
     try {
           // Process the activity message
        log.info("Received activity: {}", activity.getUserId());
        // Add your processing logic here
        activityAiService.generateRecommendations(activity);
     } catch (Exception e) {
        log.error("Error processing activity", e);
        e.printStackTrace();
     }
    }


}
