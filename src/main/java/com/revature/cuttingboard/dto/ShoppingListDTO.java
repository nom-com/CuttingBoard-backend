package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.ShoppingList;

public class ShoppingListDTO {

	private int id;
	private IngredientsDTO ingredient;
	
	public ShoppingListDTO() {
		super();
	}
	
	public ShoppingListDTO(int id, IngredientsDTO ingredient) {
		this.id = id;
		this.ingredient = ingredient;
	}

	public ShoppingListDTO(ShoppingList shoppingList) {
		this.id = shoppingList.getId();
		this.ingredient = new IngredientsDTO(shoppingList.getIngredient());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IngredientsDTO getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientsDTO ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		return "ShoppingListDTO [id=" + id + ", ingredient=" + ingredient + "]";
	}
}
