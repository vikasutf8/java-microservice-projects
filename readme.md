

## userService
PORT : 8081
- Register user
POST : http://localhost:8081/api/users/register
{
    "firstName": "Vikas",
    "lastName": "Arya",
    "email": "vikasarya@gmail.com",
    "password": "123456"
}

response
{
    "id": "1",
    "firstName": "Vikas",
    "lastName": "Arya",
    "email": "vikasarya@gmail.com",
    "password": "123456",
    "createdAt": "2023-05-01T12:00:00",
    "updatedAt": "2023-05-01T12:00:00"
}

- Get user profile
GET : http://localhost:8080/api/users/1

response
{
    "id": "1",
    "firstName": "Vikas",
    "lastName": "Arya",
    "email": "vikasarya@gmail.com",
    "password": null,
    "createdAt": "2023-05-01T12:00:00",
    "updatedAt": "2023-05-01T12:00:00"
}

- Get all users
GET : http://localhost:8080/api/users/allUser

response
[
    {
        "id": "1",
        "firstName": "Vikas",
        "lastName": "Arya",
        "email": "vikasarya@gmail.com",
        "password": null,
        "createdAt": "2023-05-01T12:00:00",
        "updatedAt": "2023-05-01T12:00:00"
    }
]


## activityService
- Track activity
POST : http://localhost:8082/api/activities
{
    "userId": "123456",
    "activityType": "RUNNING",
    "caloriesBurned": 250,
    "duration": 30,
    "startTime": "2025-09-17T07:30:00",
    "additionalMatrics": {
        "distance": 5.2,
        "steps": 7000
    }
}

response
{
    "id": "1",
    "userId": "123456",
    "activityType": "RUNNING",
    "caloriesBurned": 250,
    "duration": 30,
    "startTime": "2025-09-17T07:30:00",
    "additionalMatrics": {
        "distance": 5.2,
        "steps": 7000
    },
    "createdAt": "2023-05-01T12:00:00",
    "updatedAt": "2023-05-01T12:00:00"
}


## eureka
```
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

```
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

## Inter-service communication :EUREKA:

![alt text](image.png)


## ai Service
PORT : 8083
- Get user all recommendations
GET : http://localhost:8083/api/recommendations/user/123456

response
[
    {
        "id": "1",
        "activityId": "1",
        "userId": "123456",
        "recommendation": "Run for 30 minutes",
        "improvements": [
            "Increase calories burned",
            "Increase duration"
        ],
        "suggestions": [
            "Increase calories burned",
            "Increase duration"
        ],
        "safety": [
            "Increase calories burned",
            "Increase duration"
        ],
        "createdAt": "2023-05-01T12:00:00",
        "updatedAt": "2023-05-01T12:00:00"
    }
]

- Get activity recommendations
GET : http://localhost:8083/api/recommendations/activity/1

response
{
    "id": "1",
    "activityId": "1",
    "userId": "123456",
    "recommendation": "Run for 30 minutes",
    "improvements": [
        "Increase calories burned",
        "Increase duration"
    ],
    "suggestions": [
        "Increase calories burned",
        "Increase duration"
    ],
    "safety": [
        "Increase calories burned",
        "Increase duration"
    ],
    "createdAt": "2023-05-01T12:00:00",
    "updatedAt": "2023-05-01T12:00:00"
} 

---


## Kafka on Docker
PORT : 9092

### Docker commands
```
docker run -d -p 9092:9092 apache/kafka:latest

```
```
kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: activity-processing-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
    properties:
      spring.json.trusted.packages: "*"
      spring.json.value.type.headers: false
      spring.json.value.default.type: package com.AiFitness.aiService.model.Activity

```

```
kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer

```


![kafka.jpg](kafka.png)


## GEMINI API

https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent
headers
Content-Type: application/json
x-goog-api-key: YOUR_API_KEY

{
  "contents": [
    {
      "parts": [
        {
          "text": "Run for 30 minutes"
        }
      ]
    }
  ]
}

response
{
    "candidates": [
        {
            "content": {
                "parts": [
                    {
                        "text": "AI learns from data to make predictions or decisions.\n" 
                    }
                ],
                "role": "model"
            },
            "finishReason": "STOP",
            "avgLogprobs": -0.067390631545673721
        }
    ],
 ------..................
}