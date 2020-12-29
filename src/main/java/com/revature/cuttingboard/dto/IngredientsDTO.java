package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.Ingredients;

/**
 * DTO class to hold data about ingredients to be returned to the client
 * @author nom.com
 * @since 1.0
 *
 */

public class IngredientsDTO {

	private int id;
	private String ingredient;

	public IngredientsDTO() {
		super();
	}

	public IngredientsDTO(int id, String ingredient) {
		super();
		this.id = id;
		this.ingredient = ingredient;
	}
	
	public IngredientsDTO(Ingredients ingredient) {
		this.id = ingredient.getId();
		this.ingredient = ingredient.getIngredient();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		return "IngredientsDTO [id=" + id + ", ingredient=" + ingredient + "]";
	}
	
	
}
