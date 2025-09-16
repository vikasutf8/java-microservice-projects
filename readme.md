

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