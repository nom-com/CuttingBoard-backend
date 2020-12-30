# Cutting Board 

## Backend Overview
This project will act as the backend for the Cutting Board web application. This will handle all database interactions and other backend functionality pertaining to this application. 

This application allow users to find new favorite recipes, as well as share their own with the community. 

# Endpoints
## Base URL
The base URL to interact with this API should follow the following convention:
> http://{server-ip[:port-number]}/cuttingboard
## Create User Endpoint
> POST /user

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

## Login Endpoint
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
> POST /instructions


A request to this endpoint should contain a JSON body such as the following:
(all fields are required)
```json
{
    "step": "Example instructions here."
}
```
A correct response should be an HTTP status of 201.




## Ingredients endpoints
These endpoints are all authenticated and will require a valid JWT token to be included in the request header.  

> GET /ingredients

A get request to the /ingredients endpoint will return a list of all the ingredients listed in the ingredients table. A successful request will return a 200 HTTP status and a body like the following:
```json 
[
    {
        "id": 1,
        "ingredient": "Garlic"
    },
    {
        "id": 2,
        "ingredient": "Parmesean Cheese"
    },
    {
        "id": 3,
        "ingredient": "Salt"
    }
]
```


> GET /ingredients/{search}

A GET request to /ingredients with a search string will search the ingredients table by ingredient name. This search is case insensitive and non exact, meaning that values will be returned even if the string in the DB is not an exact match to what was entered. 

for example, a search to /ingredients/ar would return something like:
```json
[
    {
        "id": 1,
        "ingredient": "Garlic"
    },
    {
        "id": 2,
        "ingredient": "Parmesean Cheese"
    }
]
```
A 200 status code will indicate a successful request. 

> GET /ingredients/id/{id}

A request to this endpoint with an included id will return the ingredient with the specified id value. There is no request body for this request

A successful response will return a 200 status, and a single ingredient response like the following. 
```json
{
    "id": 3,
    "ingredient": "salt"
}
```

> POST /ingredients

A POST request to this endpoint will add a new ingredient to the ingredients table. A post request should include a JSON body with the ingredient name, like the following:
```json
{
   "ingredient": "Salt"
}
```

A successful request will return a 201 status and the id value and entered name of the ingredient. 

```json
{
    "id": 3,
    "ingredient": "Salt"
}
```

> PUT /ingredients/{id}

A PUT request to /ingredients with an included id will update the ingredient with the specified id value. The request body should contain the desired ingredient update. 
```json
{
   "ingredient": "salt"
}
```

A successful response will return a 200 status and a response body similar tot he following:
```json
{
    "id": 3,
    "ingredient": "salt"
}
```

>DELETE /ingredients/{id}

A DELETE request with an associated id value will delete the specified record from the ingredients table. A successful request will return a 204 status code and will have no content in the reponse body. 

## Amount Endpoints
> GET /amount/{id}

A request to this endpoint will return the details of the amount record with the specified id. A status 400 will be returned if the id does not exist. 

> POST /amount

A POST request to this endpoint will insert a new amount record into the database. The request body should contain data necessary for a new amount record like the following:
```json
{
    "amount": "6 cloves",
    "ingredientId": 1
}
```
The connected ingredient is included as an ingredient id (value can be obtained from the ingredients endpoint). A successful response should have a 201 status code and a response body like the following:
```json
{
    "id": 8,
    "amount": "6 cloves",
    "ingredient": {
        "id": 1,
        "ingredient": "Garlic"
    }
}
```

>PUT /amount/{id}

A request to this endpoint will update the amount section of an amount record. All that is needed in the request body is the new amount.
```json
{
    "amount": "5 cloves"
}
```
A successful response will return a 200 status code, and a response body with the updated amount information. 
```json 
{
    "id": 8,
    "amount": "5 cloves",
    "ingredient": {
        "id": 1,
        "ingredient": "Garlic"
    }
}
```

> DELETE /amount/{id}

A request to this endpoint will delete the amount record with the specified id value. A successful request will receive a status code 204 in response. 

## Category Endpoints
>POST /category

A request to this endpoint should contain a JSON body such as the following:
(all fields are required)
```json
{
    "category": "Example Category"
}
```

A correct response should be an HTTP status of 201 and a response body such as the following:
```json
{
    "id": 1,
    "category": "Example Category"
}
```
>GET /category

A request to this endpoint should return an HTTP status of 200 and a JSON body such as the following:

```json
{
    "id": 1,
    "category": "Example Category"
},
{
    "id": 2,
    "category": "Another Category"
},
{
    "id": 3,
    "category": "Yet Another Catgeory"
}
```
>GET /category/id/{id}

A request to this endpoint should contain a number in place of {id} such as the following:

http://localhost:8080/cuttingboard/category/id/1

A correct response should return an HTTP status of 200 and a response body such as the following:
```json
{
    "id": 1,
    "category": "Example Category"
}
```
>GET /category{name}

A request to this endpoint should contain a number in place of {name} such as the following:

http://localhost:8080/cuttingboard/category/Another

A correct response should return an HTTP status of 200 and a response body such as the following:
```json
{
    "id": 2,
    "category": "Another Category"
}
```

