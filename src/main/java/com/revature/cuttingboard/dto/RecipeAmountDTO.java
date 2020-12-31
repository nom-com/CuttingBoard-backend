package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.RecipeAmount;

/**
 * DTO class to hold data about recipe_amounts that are to be returned to the client
 * @author nom.com
 * @since 1.0
 *
 */

public class RecipeAmountDTO {

	private int id;
	private AmountDTO amount;
	
	public RecipeAmountDTO() {
		super();
	}

	public RecipeAmountDTO(int id, AmountDTO amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	
	public RecipeAmountDTO(RecipeAmount recipeAmount) {
		this.id = recipeAmount.getId();
		this.amount = new AmountDTO(recipeAmount.getAmount());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AmountDTO getAmount() {
		return amount;
	}

	public void setAmount(AmountDTO amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RecipeAmountDTO [id=" + id + ", amount=" + amount + "]";
	}
}
