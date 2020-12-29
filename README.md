# Cutting Board 

## Backend Overview
This project will act as the backend for the Cutting Board web application. This will handle all database interactions and other backend functionality pertaining to this application. 

This application allow users to find new favorite recipes, as well as share their own with the community. 

# Endpoints
## Base URL
The base URL to interact with this API should follow the following convention:
> http://{server-ip[:port-number]}/cuttingboard
## Create User Endpoint
> POST /user/create

A request to this endpoint should contain a JSON body such as the following:
(all fields are required)
```json
{
    "username": "basicuser",
    "password": "password",
    "email": "test@testing.org",
    "firstName": "Test",
    "lastName": "Tester"
}
```
A correct response should be an HTTP status of 201 and a response body similar to the following:
```json
{
    "id": 18,
    "username": "basicuser",
    "admin": false
}
```
Authentication is not required for this endpoint.

## Login/Logout Endpoint
> POST /login

A request to this endpoint should contain a JSON body such as the following:
(all fields are required)
```json
{
    "username": "basicuser",
    "password": "password"
}
```
A correct response should be an HTTP status of 200.

## Create Instructions Endpoint
> POST /instructions/create

A request to this endpoint should contain a JSON body such as the following:
(all fields are required)
```json
{
    "step": "Example instructions here."
}
```
A correct response should be an HTTP status of 201.
