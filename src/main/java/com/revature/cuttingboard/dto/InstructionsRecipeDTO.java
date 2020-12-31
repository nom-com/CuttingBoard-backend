package com.revature.cuttingboard.dto;

import com.revature.cuttingboard.model.InstructionsRecipe;

/**
 * DTO class to hold data pertaining to the instructions_recipes to be returned to the client
 * @author nom.com
 * @since 1.0
 *
 */
public class InstructionsRecipeDTO implements Comparable<InstructionsRecipeDTO>{

	private int id;
	private InstructionsDTO step;
	private int stepOrder;
	
	public InstructionsRecipeDTO() {
		super();
	}

	public InstructionsRecipeDTO(int id, InstructionsDTO step, int stepOrder) {
		super();
		this.id = id;
		this.step = step;
		this.stepOrder = stepOrder;
	}
	
	public InstructionsRecipeDTO(InstructionsRecipe instructionsRecipe) {
		this.id = instructionsRecipe.getId();
		this.step = new InstructionsDTO(instructionsRecipe.getInstruction());
		this.stepOrder = instructionsRecipe.getStepOrder();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InstructionsDTO getStep() {
		return step;
	}

	public void setStep(InstructionsDTO step) {
		this.step = step;
	}

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}

	@Override
	public String toString() {
		return "InstructionsRecipeDTO [id=" + id + ", step=" + step + ", stepOrder=" + stepOrder + "]";
	}

	@Override
	public int compareTo(InstructionsRecipeDTO o) {
		if (this.getStepOrder() > o.getStepOrder()) {
			return 1;
		} else {
			return -1;
		}
	}	
}
