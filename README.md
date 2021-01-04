# Cutting Board 

## Project Description
This project will act as the backend for the Cutting Board web application. This will handle all database interactions and other backend functionality pertaining to this application. 

This application allow users to find new favorite recipes, as well as share their own with the community. 

A public version of this API can be accessed at http://api.generictech.org.

# Technologies Used
- Java 8
- Spring Framework 5.2.12
    - Spring MVC
    - Spring AOP
- Aspectjweaver 1.9.6
- Postgresql
- JSON Web Tokens
- Hibernate 5.4.25
- Jackson Databind
- Log4j
- Apache Tomcat 9.0.41

# Features
- Create user accounts
- CRUD operations for recipes
- CRUD operations for Ingredients
- CRUD operations for Categories
- Add and delete for user favorite recipes
- Add and delete for shopping list items

# Getting Started
- In order to run this application, install and configure the following:
    - Java 8
    - Maven
    - Apache Tomcat 9+
    - Postgresql

- Once the above technologies are configured be sure the following environment variables are set properly:
    - JAVA_HOME (location of java installation)
    - CATALINA_HOME (location of tomcat installation)
    - DB_URL (URL path to your configured database)
    - DB_USERNAME (username for your database user)
    - DB_PASSWORD (password for above database user)
    - TOKEN_SECRETS (unique and secure secret string for use with JWTs)

- At this point be sure to clone the repository with the following command:
    > git clone https://github.com/2011JavaReact/jwilsonProject0.git

- Navigate to the directory created by the above command, and run the following command to package the application:
    > mvn clean package

- Once completed, copy the cuttingboard.war file from the target directory into the webapps directory of your tomcat installation. 

- Start your tomcat server and begin using the application

# Usage
## Base URL
The base URL to interact with this API should follow the following convention:
> http://{server-ip[:port-number]}/cuttingboard
## Create User Endpoint

    POST /user

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

    POST /login

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

    POST /instructions


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

    GET /ingredients

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

    GET /ingredients/{search}

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

    GET /ingredients/id/{id}

A request to this endpoint with an included id will return the ingredient with the specified id value. There is no request body for this request

A successful response will return a 200 status, and a single ingredient response like the following. 
```json
{
    "id": 3,
    "ingredient": "salt"
}
```

    POST /ingredients

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

    PUT /ingredients/{id}

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

    DELETE /ingredients/{id}

A DELETE request with an associated id value will delete the specified record from the ingredients table. A successful request will return a 204 status code and will have no content in the reponse body. 

## Amount Endpoints
    
    GET /amount/{id}

A request to this endpoint will return the details of the amount record with the specified id. A status 400 will be returned if the id does not exist. 

    POST /amount

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

    PUT /amount/{id}

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

    DELETE /amount/{id}

A request to this endpoint will delete the amount record with the specified id value. A successful request will receive a status code 204 in response. 

## Category Endpoints
    
    POST /category

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
    GET /category

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
    GET /category/id/{id}

A request to this endpoint should contain a number in place of {id} such as the following:

http://localhost:8080/cuttingboard/category/id/1

A correct response should return an HTTP status of 200 and a response body such as the following:
```json
{
    "id": 1,
    "category": "Example Category"
}
```
    GET /category{name}

A request to this endpoint should contain a number in place of {name} such as the following:

http://localhost:8080/cuttingboard/category/Another

A correct response should return an HTTP status of 200 and a response body such as the following:
```json
{
    "id": 2,
    "category": "Another Category"
}
```

## Recipe Endpoints

    GET /recipe

A request to this endpoint will return a list of all public recipes in the database. A successful request will return a 200 status, and a response body similar to the following:
```json
[
    {
        "id": 6,
        "imageLocation": "pita.jpeg",
        "title": "Pita",
        "description": "Roung pita disks",
        "publicRecipe": true,
        "ingredients": [
            {
                "id": 16,
                "amount": {
                    "id": 17,
                    "amount": "1 clove crushed",
                    "ingredient": {
                        "id": 1,
                        "ingredient": "Garlic"
                    }
                }
            },
            {
                "id": 15,
                "amount": {
                    "id": 16,
                    "amount": "2 tbsp",
                    "ingredient": {
                        "id": 2,
                        "ingredient": "Salt"
                    }
                }
            },
            {
                "id": 14,
                "amount": {
                    "id": 15,
                    "amount": "4 cups",
                    "ingredient": {
                        "id": 3,
                        "ingredient": "Flour"
                    }
                }
            }
        ],
        "instructions": [
            {
                "id": 8,
                "step": {
                    "id": 6,
                    "step": "knead until smooth"
                },
                "stepOrder": 1
            },
            {
                "id": 9,
                "step": {
                    "id": 7,
                    "step": "let rest for 45-90 min"
                },
                "stepOrder": 2
            },
            {
                "id": 10,
                "step": {
                    "id": 8,
                    "step": "punch down dough"
                },
                "stepOrder": 3
            },
            {
                "id": 11,
                "step": {
                    "id": 9,
                    "step": "spread dough out and cut into circles, place each on a greased cookie sheet"
                },
                "stepOrder": 4
            },
            {
                "id": 12,
                "step": {
                    "id": 10,
                    "step": "bake at 375 for 27-30 min"
                },
                "stepOrder": 5
            }
        ],
        "category": {
            "id": 1,
            "category": "Bread"
        }
    },
    ...
]
```

    GET /recipe/recommended

A request to this endpoint will return the top 10 recipies from the database. This is determined by the recipies that have been favorited the most by the users. 

A successful response will return a list of the top 10 most popular recipes, in the same format as the above response. A successful request will also have a status code 200.

No authentication is needed for this particular endpoint. 

    GET /recipe/id/{id}

A request to this endpoint will return the recipe with the specified id value. A successful response will contain a 200 status, and will have a response body of a single recipe (same format as a single element from the above response body).

    GET /recipe/{search}

A request to this endpoint will search the database for all recipes whoes titles, descriptions or categories contain the string provided in the request.

A successful response will return a 200 status code and a list of the recipes matching the criteria. (example output matches the GET all detailed above.)

    GET /recipe/category/{id}

A request to this endpoint will return a list of recipes that have the category with the specified id value. This will allow clients to search by category alone. 

Successful responses will also return a 200 status with a list of recipes as shown above. 

    POST /recipe

A request to this endpoint will add a new recipe to the database. A request should be sent with the recipe data in the request body, formatted like the following example: 
```json
{
    "imageLocation": "garlicoil.jpeg",
    "title": "Garlic Oil",
    "description": "To die for garlic oil",
    "publicRecipe": true,
    "ingredients": [
        {
            "amount": {
                "amount": "12 cloves",
                "ingredient": {
                    "id": 1
                }
            }
        },
        {
            "amount": {
                "amount": "2 cups",
                "ingredient": {
                    "id": 5
                }
            }
        },
        {
            "amount": {
                "amount": "a pinch",
                "ingredient": {
                    "id": 2
                }
            }
        }
    ],
    "instructions": [
        {
            "step": {
                "step": "Peel skin off garlic, and place in sauce pan"
            },
            "stepOrder": 1
        },
        {
            "step": {
                "step": "pour oil over garlic"
            },
            "stepOrder": 2
        },
        {
            "step": {
                "step": "sprinkle salt into the garlic and oil mixture, stir to incorporate"
            },
            "stepOrder": 3
        },
        {
            "step": {
                "step": "place in oven at 375 for 2 hours"
            },
            "stepOrder": 4
        }
    ],
    "category": {
        "id": 3
    }
}
```

a successful response will return a status code 201, and a response body with the recipe details. (See GET /recipe for an example of what the output would look like).

    PUT /recipe/{id}

A request to this endpoint will update a recipe with new values. The request body should look identical to that of the POST request, and all values must be included in order to update. 

All values can be changed, and new ingredients and steps can be added. (At this point steps and ingredients cannot be deleted through this endpoint);

A successful request will return a 200 status code with the updated data in the respinse body (also identical to the POST response.)

    DELETE /recipe/{id}

A request to this endpoint will delete the recipe associated with the provided id value. This will also delete all instructions and amounts associated with this recipe, as well as favorites listings. 

A successful response will return a status code 204. 


## Shopping List Endpoints
    POST /shoppinglist

A request to this endpoint should contain a JSON body such as the following:
(all fields are required)
```json
{
    "ingredient": 
    {
        "id":"3"
    }
}
```

A correct response should be an HTTP status of 201 and a response body such as the following:
```json
{
    "id": 1,
    "ingredient":
    {
        "id": 3,
        "ingredient": "sugar"
     }
}
```

    GET /shoppinglist

A request to this endpoint should return an HTTP status of 200 and a JSON body such as the following:

```json
{
    "id": 1
    {
        "id": 1,
        "ingredient":
        {
            "id": 3,
            "ingredient": "sugar"
         }
    }
}
```

    GET /shoppinglist/id/{id}

A request to this endpoint should contain a number in place of {id} such as the following:

http://localhost:8080/cuttingboard/shoppinglist/id/2

A request to this endpoint should return an HTTP status of 200 and a JSON body such as the following:

```json
{
    "id": 2
    {
        "id": 1,
        "ingredient":
        {
            "id": 4,
            "ingredient": "flour"
         }
    }
}
```

    DELETE /shoppinglist/{id}

A request to this endpoint should contain a number in place of {id} such as the following:

http://localhost:8080/cuttingboard/shoppinglist/1

A correct response should return an HTTP status of 204 and an empty response body.

## User Favorites Endpoints
    
    POST /favorites/{id}

A request to this endpoint should contain a number (the id of the recipe) in place of {id} such as the following:

http://localhost:8080/cuttingboard/favorites/1

A correct response should be an HTTP status of 201 and a response body such as the following:
```json
{
    "id": 8,
    "recipe": {
        "id": 1,
        "imageLocation": "image.jpg",
        "title": "f",
        "description": "f",
        "publicRecipe": false,
        "ingredients": [],
        "instructions": [],
        "category": {
            "id": 1,
            "category": "Test Category"
        }
    }
}
```

    GET /favorites

A request to this endpoint should return an HTTP status of 200 and a JSON body such as the following:

```json
[
    {
        "id": 5,
        "recipe": {
            "id": 2,
            "imageLocation": "image.jpg",
            "title": "f",
            "description": "f",
            "publicRecipe": false,
            "ingredients": [],
            "instructions": [],
            "category": {
                "id": 2,
                "category": "Another Category"
            }
        }
    }
]
```

    DELETE /favorites/{id}

A request to this endpoint should contain a number (the id of the user favorite) in place of {id} such as the following:

http://localhost:8080/cuttingboard/favrites/1

A correct response should return an HTTP status of 204 and an empty response body.

    DELTE /favorites/recipe/{id}

A request to this endpoint will delete a favorite listing based upon the combination of the user id and the recipe id provided in the URI. 

A successful response will be a status code 204.

# Contributors
- Frank Johnson III (fjohnsoniii)
- Jaden Wilson (jwilson717)

# License
There is no license associated with this project