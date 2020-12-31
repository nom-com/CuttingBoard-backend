package com.revature.cuttingboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.AmountDAO;
import com.revature.cuttingboard.dao.CategoryDAO;
import com.revature.cuttingboard.dao.IngredientsDAO;
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
	private CategoryDAO categoryDao;
	@Autowired
	private IngredientsDAO ingredientsDao;

	public List<RecipeDTO> getAllRecipes() throws Exception {
		List<RecipeDTO> recipes = convertLists(recipeDao.getAllRecipes());

		return recipes;
	}
	
	public RecipeDTO getRecipeById(int id) throws Exception {
		return new RecipeDTO(recipeDao.getRecipeById(id));
	}
	
	public List<RecipeDTO> searchRecipes(String search) throws Exception {
		List<RecipeDTO> recipes = convertLists(recipeDao.searchRecipe(search));

		return recipes;
	}
	
	public List<RecipeDTO> searchRecipesByCategory(int id) throws Exception {
		List<RecipeDTO> recipes = convertLists(recipeDao.searchRecipesByCategory(id));

		return recipes;
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
	
	public RecipeDTO updateRecipe(RecipeDTO recipeData, int id,  SystemUser user) throws Exception {
		Date today = new Date();
		recipeData.setId(id);
		
		Recipe dbRecipe = recipeDao.getRecipeById(id);
		dbRecipe.setImageLocation(recipeData.getImageLocation());
		dbRecipe.setTitle(recipeData.getTitle());
		dbRecipe.setDescription(recipeData.getDescription());
		dbRecipe.setPublicRecipe(recipeData.isPublicRecipe());
		dbRecipe.setLastUpdatedBy(user);
		dbRecipe.setLastUpdateDate(today);
		Category c = categoryDao.getCategoryById(recipeData.getCategory().getId());
		dbRecipe.setCategory(c);
		dbRecipe.setIngredients(convertRecipeAmountDTOLists(dbRecipe.getIngredients(), recipeData.getIngredients(), dbRecipe.getId(), user));
		dbRecipe.setInstructions(convertInstructionsRecipeDTOLists(dbRecipe.getInstructions(), recipeData.getInstructions(), id, user));
		
		for (RecipeAmount recipeAmount: dbRecipe.getIngredients()) {
			recipeAmountDao.updateRecipeAmount(recipeAmount);
			recipeAmount.getAmount().setIngredient(ingredientsDao.getIngredientById(recipeAmount.getAmount().getIngredient().getId()));
		}
		
		for (InstructionsRecipe instructionsRecipe: dbRecipe.getInstructions()) {
			instructionsRecipeDao.updateInstructionsRecipe(instructionsRecipe);
		}
		
		return new RecipeDTO(recipeDao.updateRecipe(dbRecipe));
	}
	
	public boolean deleteRecipe(int id) {
		Recipe dbRecipe;
		try {
			dbRecipe = recipeDao.getRecipeById(id);
			
			for (RecipeAmount recipeAmount: dbRecipe.getIngredients()) {
				recipeAmountDao.deleteRecipeAmount(recipeAmount.getId());
			}
			
			for (InstructionsRecipe instructionsRecipe: dbRecipe.getInstructions()) {
				instructionsRecipeDao.deleteInstructionsRecipe(instructionsRecipe.getId());
			}
			
			recipeDao.deleteRecipe(dbRecipe.getId());
			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
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
	
	private List<RecipeAmount> convertRecipeAmountDTOLists(List<RecipeAmount> dbRecipeAmounts, List<RecipeAmountDTO> recipeAmounts, int id, SystemUser user) {
		Date today = new Date();
		Recipe recipe = new Recipe();
		recipe.setId(id);
		
		for (int i = 0; i < recipeAmounts.size(); i++) {
			
			if (i < dbRecipeAmounts.size()) {
				dbRecipeAmounts.get(i).getAmount().setAmount(recipeAmounts.get(i).getAmount().getAmount());
				dbRecipeAmounts.get(i).getAmount().getIngredient().setId(recipeAmounts.get(i).getAmount().getIngredient().getId());
				dbRecipeAmounts.get(i).getAmount().setLastUpdateDate(today);
				dbRecipeAmounts.get(i).getAmount().setLastUpdatedBy(user);
			} else {
				RecipeAmount amount = new RecipeAmount();
				amount.setRecipe(recipe);
				amount.setAmount(new Amount());
				amount.getAmount().setAmount(recipeAmounts.get(i).getAmount().getAmount());
				amount.getAmount().setIngredient(new Ingredients());
				amount.getAmount().getIngredient().setId(recipeAmounts.get(i).getAmount().getIngredient().getId());
				amount.getAmount().setCreatedBy(user);
				amount.getAmount().setCreationDate(today);
				amount.getAmount().setLastUpdatedBy(user);
				amount.getAmount().setLastUpdateDate(today);
				amount.setCreatedBy(user);
				amount.setCreationDate(today);
				dbRecipeAmounts.add(amount);
			}

			dbRecipeAmounts.get(i).setLastUpdatedBy(user);
			dbRecipeAmounts.get(i).setLastUpdateDate(today);

		}
		
		return dbRecipeAmounts;
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
	
	private List<InstructionsRecipe> convertInstructionsRecipeDTOLists(List<InstructionsRecipe> instructionsRecipe, List<InstructionsRecipeDTO> instructionsData, int id, SystemUser user){
		Date today = new Date();
		Recipe recipeId = new Recipe();
		recipeId.setId(id);
		
		for (int i = 0; i < instructionsData.size(); i++) {
			
			if (i < instructionsRecipe.size()) {
				instructionsRecipe.get(i).getInstruction().setStep(instructionsData.get(i).getStep().getStep());
				instructionsRecipe.get(i).getInstruction().setLastUpdatedBy(user);
				instructionsRecipe.get(i).getInstruction().setLastUpdateDate(today);
				instructionsRecipe.get(i).setStepOrder(instructionsData.get(i).getStepOrder());
				instructionsRecipe.get(i).setLastUpdatedBy(user);
				instructionsRecipe.get(i).setLastUpdateDate(today);
			} else {
				InstructionsRecipe instruction = new InstructionsRecipe();
				instruction.setRecipe(recipeId);
				instruction.setInstruction(new Instructions(instructionsData.get(i).getStep().getStep(), user, today, user, today));
				instruction.setStepOrder(instructionsData.get(i).getStepOrder());
				instruction.setCreatedBy(user);
				instruction.setCreationDate(today);
				instruction.setLastUpdatedBy(user); 
				instruction.setLastUpdateDate(today);
				instructionsRecipe.add(instruction);
			}
			
			
			instructionsRecipe.get(i).setLastUpdatedBy(user);
			instructionsRecipe.get(i).setLastUpdateDate(today);
		}
		
		return instructionsRecipe;
	}
}
