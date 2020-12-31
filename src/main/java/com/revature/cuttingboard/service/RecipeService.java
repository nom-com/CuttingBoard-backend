package com.revature.cuttingboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.AmountDAO;
import com.revature.cuttingboard.dao.InstructionsRecipeDAO;
import com.revature.cuttingboard.dao.RecipeAmountDAO;
import com.revature.cuttingboard.dao.RecipeDAO;
import com.revature.cuttingboard.dto.InstructionsRecipeDTO;
import com.revature.cuttingboard.dto.RecipeAmountDTO;
import com.revature.cuttingboard.dto.RecipeDTO;
import com.revature.cuttingboard.model.Amount;
import com.revature.cuttingboard.model.Category;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.model.Instructions;
import com.revature.cuttingboard.model.InstructionsRecipe;
import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.model.RecipeAmount;
import com.revature.cuttingboard.model.SystemUser;

/**
 * Service class to handle business logic pertaining to recipes
 * 
 * @author nom.com
 * @since 1.0
 *
 */
@Service
public class RecipeService {

	@Autowired
	private RecipeDAO recipeDao;
	@Autowired
	private RecipeAmountDAO recipeAmountDao;
	@Autowired
	private InstructionsRecipeDAO instructionsRecipeDao;
	@Autowired
	private AmountDAO amountDao;

	public List<RecipeDTO> getAllRecipes() throws Exception {
		List<RecipeDTO> recipes = convertLists(recipeDao.getAllRecipes());

		return recipes;
	}
	
	public RecipeDTO getRecipeById(int id) throws Exception {
		return new RecipeDTO(recipeDao.getRecipeById(id));
	}
	
	public RecipeDTO insertRecipe(RecipeDTO recipeData, SystemUser user) throws Exception {
		Date today = new Date();
		Category category = new Category();
		category.setId(recipeData.getCategory().getId());
		Recipe recipe = new Recipe(recipeData.getImageLocation(), recipeData.getTitle(), recipeData.getDescription()
				, recipeData.isPublicRecipe(), user, today, user, today, category);
		recipe = recipeDao.insertRecipe(recipe);
		List<RecipeAmount> amounts = convertRecipeAmountDTOLists(recipeData.getIngredients(), recipe.getId(), user);
		for (RecipeAmount ra: amounts) {
			recipeAmountDao.insertRecipeAmount(ra);
		}
		
		List<InstructionsRecipe> instructions = convertInstructionsRecipeDTOLists(recipeData.getInstructions(), recipe.getId(), user);
		for(InstructionsRecipe step: instructions) {
			instructionsRecipeDao.insertInstuctionsRecipe(step);
		}
		return new RecipeDTO(recipeDao.getRecipeById(recipe.getId()));
	}

	private List<RecipeDTO> convertLists(List<Recipe> dbRecipes) {
		List<RecipeDTO> recipes = new ArrayList<RecipeDTO>();

		for (Recipe r : dbRecipes) {
			recipes.add(new RecipeDTO(r));
		}

		return recipes;
	}
	
	
	private List<RecipeAmount> convertRecipeAmountDTOLists(List<RecipeAmountDTO> recipeAmounts, int id, SystemUser user) {
		Date today = new Date();
		List<RecipeAmount> amounts = new ArrayList<RecipeAmount>();
		Recipe recipeId = new Recipe();
		recipeId.setId(id);
		
		for (RecipeAmountDTO amount: recipeAmounts) {
			RecipeAmount recipeAmount = new RecipeAmount();
			recipeAmount.setCreatedBy(user);
			recipeAmount.setCreationDate(today);
			recipeAmount.setLastUpdatedBy(user);
			recipeAmount.setLastUpdateDate(today);
			recipeAmount.setAmount(new Amount());
			recipeAmount.getAmount().setCreatedBy(user);
			recipeAmount.getAmount().setCreationDate(today);
			recipeAmount.getAmount().setLastUpdatedBy(user);
			recipeAmount.getAmount().setLastUpdateDate(today);
			recipeAmount.getAmount().setIngredient(new Ingredients());
			recipeAmount.setRecipe(recipeId);
			recipeAmount.getAmount().setAmount(amount.getAmount().getAmount());
			recipeAmount.getAmount().getIngredient().setId(amount.getAmount().getIngredient().getId());
			amounts.add(recipeAmount);
		}
		
		return amounts;
	}
	
	private List<InstructionsRecipe> convertInstructionsRecipeDTOLists(List<InstructionsRecipeDTO> instructionsData, int id, SystemUser user){
		Date today = new Date();
		List<InstructionsRecipe> instructions = new ArrayList<InstructionsRecipe>();
		Recipe recipeId = new Recipe();
		recipeId.setId(id);
		
		for (InstructionsRecipeDTO data: instructionsData) {
			InstructionsRecipe step = new InstructionsRecipe();
			step.setCreatedBy(user);
			step.setCreationDate(today);
			step.setLastUpdatedBy(user);
			step.setLastUpdateDate(today);
			step.setRecipe(recipeId);
			step.setInstruction(new Instructions());
			step.getInstruction().setStep(data.getStep().getStep());
			step.getInstruction().setCreatedBy(user);
			step.getInstruction().setCreationDate(today);
			step.getInstruction().setLastUpdatedBy(user);
			step.getInstruction().setLastUpdateDate(today);
			step.setStepOrder(data.getStepOrder());	
			instructions.add(step);
		}
		
		return instructions;
	}
}
