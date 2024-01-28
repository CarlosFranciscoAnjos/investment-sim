# Use Cases

    baseUrl = https://localhost:8080

### UC1 - Register User

    POST: {baseUrl}/api/users

    {
      "name": "TOM",
      "email": "tom@gmail.com",
      "password": "qwerty",
      "role": "CLIENT"
    }

### UC2 - Login as User

    Username: TOM (or tom@gmail.com)
    password: qwerty

#### basic auth

![login-image](login.png)

### UC3 - Create a Simulation

    POST: {baseUrl}/api/simulations

    {
      "userId": {id},
      "description": "simulation-01"
    }

### UC4 - Create a Simple Asset

    POST: {baseUrl}/api/assets

    {
      "simulationId": {id},
      "description": "home-01",
      "income": 400.00,
      "value": 90000.00
    }

### UC5 - Create a Simple Liability

### UC6 - Get Simulation Overview
