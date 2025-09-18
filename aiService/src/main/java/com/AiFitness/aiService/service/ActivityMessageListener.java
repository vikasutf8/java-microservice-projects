package com.AiFitness.aiService.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.AiFitness.aiService.model.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    

    @KafkaListener(topics = "${kafka.topic.name}" , groupId = "activity-processing-group")
    public void processActivity(Activity activity) {
        // Process the activity message
        log.info("Received activity: {}", activity.getUserId());
        // Add your processing logic here
    }
}
