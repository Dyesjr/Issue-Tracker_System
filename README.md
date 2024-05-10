# Issue-Tracker_System

                                Issue Tracker

The Issue Tracker is a web application designed to help teams manage their tasks and track the progress of various 
issues or tickets. It provides functionalities for creating, updating, retrieving, and deleting issues, as well as user 
authentication for secure access.


Table of Contents
Introduction
Features
Setup
Usage
Signing Up
Logging In
Creating an Issue
Getting Issues
Updating an Issue
Deleting an Issue
Testing


Introduction
The Issue Tracker allows teams to efficiently manage their tasks and collaborate on projects by providing a centralized 
platform for tracking issues and assigning tasks. It offers user authentication to ensure secure access to the application.

Features
Create, update, retrieve, and delete issues
User authentication 
RESTful API endpoints 


Setup
Prerequisites
Java 17
Apache Maven
H2 Database


Installation
Clone the repository:

git clone git@github.com:Dyesjr/Issue-Tracker_System.git

Navigate to the project directory:

cd issue-tracker

Build the project using Maven:

mvn clean install

To generate the JWT token, we need a secret key and the token expiration time; these values are read from the 
application configuration properties file using the annotation @Value.

We must update the application.properties to define these values:

security.jwt.secret-key=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
# 1h in millisecond
security.jwt.expiration-time=3600000
The secret key must be an HMAC hash string of 256 bits; otherwise, the token generation will throw an error. I used this 
[website](https://www.devglan.com/online-tools/hmac-sha256-online?ref=blog.tericcabrel.com) to generate one.

The token expiration time is expressed in milliseconds, so remember if your token expires too soon.

Run the application


Usage

Signing Up
To sign up as a new user, send a POST request to http://localhost:8080/auth/signup with the following JSON payload:

{
"email": "trial@example.com",
"password": "yourpassword",
"fullName": "John Doe"
}

The server responds with the newly created user object.

Expected Output:
{
"userId": 1,
"fullName": "John Doe",
"email": "trial@example.com",
"password": "$2a$10$TEQBGMom1NFaYM/YI9Qc0uw1qIHhd5fW2J5.yfDQ8.wRJqC.x3NIq",
"createdAt": "2024-05-10T07:07:59.879+00:00",
"updatedAt": "2024-05-10T07:07:59.879+00:00",
"enabled": true,
"authorities": [],
"username": "trial@example.com",
"accountNonExpired": true,
"accountNonLocked": true,
"credentialsNonExpired": true
}

Logging In
To log in as an existing user, send a POST request to http://localhost:8080/auth/login with the following JSON payload:

{
"email": "example@example.com",
"password": "yourpassword"
}

The server responds with a JWT token that you can use for authentication in subsequent requests.

Expected Output:

{
"token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0cmlhbEBleGFtcGxlLmNvbSIsImlhdCI6MTcxNTMyNTAxNCwiZXhwIjoxNzE1MzI4NjE0fQ.E8hk2Sp6d1DYX04KRlpZ8SQ8UfT_tBsmnNqT7i4ntXHO7S96xsMp2UG4c007d2Jg",
"expiresIn": 3600000
}

The token that is generated, copy and back in postman, go authorization tab, in the dropdown select Bearer Token and the paste the value of token from the login output to Postman.

Once this is done then you can move to the issues.

Creating an Issue
To create a new issue, send a POST request to  http://localhost:8080/api/issues with the following JSON payload:

{
"title": "Example Issue",
"description": "Description of the issue",
"assignee": "El Chapo",
"priority": "HIGH"
}

The server responds with the ID of the newly created issue.

Getting Issues

To retrieve all issues, send a GET request to http://localhost:8080/api/issues. The server responds with a list of issue
objects.
Sample output:

[
{
"issueId": 1,
"title": "Example Issue",
"description": "Description of the issue",
"assignee": "El Chapo",
"priority": "HIGH"
},
{
"issueId": 2,
"title": "Example Issue 2",
"description": "Description of the issue",
"assignee": "El Chapos",
"priority": "LOW"
},
{
"issueId": 3,
"title": "Example Issue 3",
"description": "Description of the issue",
"assignee": "El Chapos",
"priority": "LOW"
}
]

To retrieve a specific issue by ID, send a GET request to http://localhost:8080/api/issues/{issueId} where {issueId} is 
the ID of the issue you want to retrieve. The server responds with the issue object if found, or a 404 error if not 
found.

Sample Output after hitting  http://localhost:8080/api/issues/1:

{
"issueId": 1,
"title": "Example Issue",
"description": "Description of the issue",
"assignee": "El Chapo",
"priority": "HIGH"
}

Updating an Issue
To update an existing issue, send a PUT request to http://localhost:8080/api/issues/{issueId} with the following JSON 
payload:

{
"title": "Updated Issue Title",
"description": "Updated description of the issue",
"assignee": "Updated Assignee Name",
"priority": "LOW"
}

The server responds with a 200 status success message if the update is successful and an error message if not.

You can verify this update by retrieving the issue(s)

Deleting an Issue
To delete an existing issue, send a DELETE request to  http://localhost:8080/api/issues/{issueId} where {issueId} is the 
ID of the issue you want to delete. The server responds with a 204 (no content) success message if the deletion is 
successful.

Testing
Unit tests and integration tests are included in the project.
Run tests using Maven:

mvn test


License

This project is licensed under the MIT License.