package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.Amount;

/**
 * DTO class to hold data about amounts to be returned to the client
 * @author nom.com
 * @since 1.0
 *
 */

public class AmountDTO {

	private int id;
	private String amount;
	private IngredientsDTO ingredient;
	
	public AmountDTO() {
		super();
	}

	public AmountDTO(int id, String amount, IngredientsDTO ingredient) {
		super();
		this.id = id;
		this.amount = amount;
		this.ingredient = ingredient;
	}
	
	public AmountDTO(Amount amount) {
		this.id = amount.getId();
		this.amount = amount.getAmount();
		this.ingredient = new IngredientsDTO(amount.getIngredient());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public IngredientsDTO getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientsDTO ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		return "AmountDTO [id=" + id + ", amount=" + amount + ", ingredient=" + ingredient + "]";
	}	
}
