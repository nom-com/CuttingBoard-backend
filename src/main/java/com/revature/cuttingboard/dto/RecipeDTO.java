package com.revature.cuttingboard.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.cuttingboard.model.Category;
import com.revature.cuttingboard.model.InstructionsRecipe;
import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.model.RecipeAmount;

/**
 * DTO class for handling data returned to client for a recipe. 
 * @author nom.com
 * @since 1.0
 *
 */
public class RecipeDTO {

	private int id;
	private String imageLocation;
	private String title;
	private String description;
	private boolean publicRecipe;
	private List<RecipeAmountDTO> ingredients;
	private List<InstructionsRecipeDTO> instructions;
	private CategoryDTO category;
	
	public RecipeDTO() {
		super();
	}
	
	
	public RecipeDTO(int id, String imageLocation, String title, String description, boolean publicRecipe,
			List<RecipeAmountDTO> ingredients, List<InstructionsRecipeDTO> instructions, CategoryDTO category) {
		super();
		this.id = id;
		this.imageLocation = imageLocation;
		this.title = title;
		this.description = description;
		this.publicRecipe = publicRecipe;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.category = category;
	}

	public RecipeDTO(Recipe recipe) {
		this.id = recipe.getId();
		this.imageLocation = recipe.getImageLocation();
		this.title = recipe.getTitle();
		this.description = recipe.getDescription();
		this.publicRecipe = recipe.isPublicRecipe();
		this.ingredients = new ArrayList<RecipeAmountDTO>();
		this.instructions = new ArrayList<InstructionsRecipeDTO>();
		this.category = new CategoryDTO(recipe.getCategory());
		
		for (RecipeAmount amount: recipe.getIngredients()) {
			this.ingredients.add(new RecipeAmountDTO(amount));
		}
		
		for (InstructionsRecipe instruction: recipe.getInstructions()) {
			this.instructions.add(new InstructionsRecipeDTO(instruction));
		}
		Collections.sort(this.instructions);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isPublicRecipe() {
		return publicRecipe;
	}

	public void setPublicRecipe(boolean publicRecipe) {
		this.publicRecipe = publicRecipe;
	}

	public List<RecipeAmountDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeAmountDTO> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<InstructionsRecipeDTO> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<InstructionsRecipeDTO> instructions) {
		this.instructions = instructions;
	}
	
	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "RecipeDTO [id=" + id + ", imageLocation=" + imageLocation + ", title=" + title + ", description="
				+ description + ", publicRecipe=" + publicRecipe + ", ingredients=" + ingredients + ", instructions="
				+ instructions + ", category=" + category + "]";
	}
	
}
