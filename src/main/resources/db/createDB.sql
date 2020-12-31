-- Be sure to run this in a schema named cuttingboard in order for the backend server 
-- to connect to it properly. 
DROP TABLE IF EXISTS shopping_list;
DROP TABLE IF EXISTS user_favorites;
DROP TABLE IF EXISTS instructions_recipe;
DROP TABLE IF EXISTS instructions;
DROP TABLE IF EXISTS recipe_amount;
DROP TABLE IF EXISTS amount;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS system_user;

CREATE TABLE system_user
( system_user_id SERIAL PRIMARY KEY 
, username VARCHAR(45) NOT NULL UNIQUE
, password VARCHAR(75) NOT NULL
, email VARCHAR(45) NOT NULL UNIQUE
, first_name VARCHAR(45) NOT NULL
, last_name VARCHAR(45) NOT NULL
, creation_date DATE NOT NULL
, last_update_date DATE NOT NULL
, admin boolean NOT NULL
, salt VARCHAR(75) NOT NULL);

CREATE TABLE category
( category_id SERIAL PRIMARY KEY
, category VARCHAR(45) NOT NULL UNIQUE 
, creation_date DATE NOT NULL
, created_by INT NOT NULL
, CONSTRAINT fk_category_1 FOREIGN KEY (created_by) 
	REFERENCES SYSTEM_USER(system_user_id));
	
CREATE TABLE recipe 
( recipe_id SERIAL PRIMARY KEY 
, image_location VARCHAR(45) NOT NULL 
, title VARCHAR(45) NOT NULL 
, description VARCHAR(255)
, public BOOLEAN NOT NULL
, created_by INT NOT NULL
, creation_date DATE NOT NULL 
, last_updated_by INT NOT NULL
, last_update_date DATE NOT NULL
, category_id INT NOT NULL
, CONSTRAINT fk_recipe_1 FOREIGN KEY (created_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_recipe_2 FOREIGN KEY (last_updated_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_recipe_3 FOREIGN KEY (category_id)
	REFERENCES category(category_id));

CREATE TABLE ingredients
( ingredients_id SERIAL PRIMARY KEY 
, ingredient VARCHAR(45) NOT NULL UNIQUE
, created_by INT NOT NULL
, creation_date DATE NOT NULL
, last_updated_by INT NOT NULL
, last_update_date DATE NOT NULL
, CONSTRAINT fk_ingredients_1 FOREIGN KEY (created_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_ingredients_2 FOREIGN KEY (last_updated_by)
	REFERENCES system_user(system_user_id));

CREATE TABLE amount 
(amount_id SERIAL PRIMARY KEY
, amount VARCHAR(45) NOT NULL
, ingredient_id INT NOT NULL
, created_by INT NOT NULL
, creation_date DATE NOT NULL
, last_updated_by INT NOT NULL
, last_update_date DATE NOT NULL
,CONSTRAINT fk_amount_1 FOREIGN KEY (created_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_amount_2 FOREIGN KEY (last_updated_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_amount_3 FOREIGN KEY (ingredient_id)
	REFERENCES ingredients(ingredients_id));

CREATE TABLE recipe_amount
( recipe_amount_id SERIAL PRIMARY KEY
, recipe_id INT NOT NULL
, amount_id INT NOT NULL
, created_by INT NOT NULL
, creation_date DATE NOT NULL
, last_updated_by INT NOT NULL
, last_update_date DATE NOT NULL
, CONSTRAINT fk_recipe_amount_1 FOREIGN KEY (created_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_recipe_amount_2 FOREIGN KEY (last_updated_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_recipe_amount_3 FOREIGN KEY (recipe_id)
	REFERENCES recipe(recipe_id)
, CONSTRAINT fk_recipe_amount_4 FOREIGN KEY (amount_id)
	REFERENCES amount(amount_id));
	
CREATE TABLE instructions 
( instructions_id SERIAL PRIMARY KEY 
, step VARCHAR(255) NOT NULL
, created_by INT NOT NULL
, creation_date DATE NOT NULL
, last_updated_by INT NOT NULL
, last_update_date DATE NOT NULL
, CONSTRAINT fk_instructions_1 FOREIGN KEY (created_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_instructions_2 FOREIGN KEY (last_updated_by)
	REFERENCES system_user(system_user_id));
	
CREATE TABLE instructions_recipe
( instructions_recipe_id SERIAL PRIMARY KEY 
, instructions_id INT NOT NULL
, recipe_id INT NOT NULL
, step_order INT NOT NULL
, created_by INT NOT NULL
, creation_date DATE NOT NULL
, last_updated_by INT NOT NULL
, last_update_date DATE NOT NULL
, CONSTRAINT fk_instructions_recipe_1 FOREIGN KEY (created_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_instructions_recipe_2 FOREIGN KEY (last_updated_by)
	REFERENCES system_user(system_user_id)
, CONSTRAINT fk_instructions_recipe_3 FOREIGN KEY (instructions_id)
	REFERENCES instructions(instructions_id)
, CONSTRAINT fk_instructions_recipe_4 FOREIGN KEY (recipe_id)
	REFERENCES recipe(recipe_id));
	
CREATE TABLE user_favorites
( user_favorites_id SERIAL PRIMARY KEY 
, recipe_id INT NOT NULL
, system_user_id INT NOT NULL
, creation_date DATE NOT NULL
, CONSTRAINT fk_user_favorites_1 FOREIGN KEY (recipe_id) 
	REFERENCES recipe(recipe_id)
, CONSTRAINT fk_user_favorites_2 FOREIGN KEY (system_user_id)
	REFERENCES system_user(system_user_id));
	
CREATE TABLE shopping_list
( shopping_list_id SERIAL PRIMARY KEY
, ingredients_id INT NOT NULL
, system_user_id INT NOT NULL
, creation_date DATE NOT NULL
, CONSTRAINT fk_shopping_list_1 FOREIGN KEY (ingredients_id)
	REFERENCES ingredients(ingredients_id)
, CONSTRAINT fk_shopping_list_2 FOREIGN KEY (system_user_id)
	REFERENCES system_user(system_user_id));