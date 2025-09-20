package com.AiFitness.aiService.service;

import org.springframework.stereotype.Service;

import com.AiFitness.aiService.model.Activity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class ActivityAiService {

    private final GeminiService geminiService;

    public void generateRecommendations(Activity activity) {
        String prompt = createPromptForActivity(activity);
        log.info("Prompt for activity: {}", geminiService.getRecommendations(prompt));
    }

    private String createPromptForActivity(Activity activity) {
        return String.format("""
                You are an AI assistant that helps people find activities based on their interests and provide detailled recommendation in the following EXACT JSON format. 
                {
                "analysis": {
                    "overall": "OVerall analysis of the activity",
                    "pace": "Pace of the activity",
                    "heart rate": "Heart rate of the activity",
                    "caloriesBurned": "Calories burned during the activity"
                },
                "improvements": [
                    {
                    "area": "Area name",
                    "recommendation": "Detail Recommendation "
                    }
                ],
                "suggestions": [
                    {
                    "workout": "Workout name",
                    "description": "Description of the workout"
                    }
                ],
                "safety": [
                    "safety point 1",
                    "safety point 2"
                ]
                }

                Analyze the following activity:-
                Activity Type : %s
                Duration : %d mins
                Calories Burned : %d
                Additional Matrix : %s

                Provide details analysis focused on the activity perfomance ,improvements,next workout suggestions and Safety with corrections.Ensure the output is in JSON format above shown EXACTLY.
                
                """, activity.getActivityType(), activity.getDuration(), activity.getCaloriesBurned(), activity.getAdditionalMatrics());
    }

}
