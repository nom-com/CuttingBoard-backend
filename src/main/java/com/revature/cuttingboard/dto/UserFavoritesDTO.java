package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.UserFavorites;

public class UserFavoritesDTO {

	private int id;
	private RecipeDTO recipe;
	
	public UserFavoritesDTO() {
		super();
	}
	
	public UserFavoritesDTO(int id, RecipeDTO recipe) {
		this.id = id;
		this.recipe = recipe;
	}
	
	public UserFavoritesDTO(UserFavorites userFavorites) {
		this.id = userFavorites.getId();
		this.recipe = new RecipeDTO(userFavorites.getRecipe());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RecipeDTO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDTO recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "UserFavoritesDTO [id=" + id + ", recipe=" + recipe + "]";
	}
}
