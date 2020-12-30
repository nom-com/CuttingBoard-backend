package com.revature.cuttingboard.dto;

/**
 * DTO class to get data from the client for processing a new amount record
 * @author nom.com
 * @since 1.0
 *
 */

public class AmountDataDTO {

	private String amount;
	private int ingredientId;
	
	public AmountDataDTO() {
		super();
	}

	public AmountDataDTO(String amount, int ingredientId) {
		super();
		this.amount = amount;
		this.ingredientId = ingredientId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	@Override
	public String toString() {
		return "AmountDataDTO [amount=" + amount + ", ingredientId=" + ingredientId + "]";
	}
	
	
}
