

## userService
- Register user
POST : http://localhost:8080/api/users/register
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