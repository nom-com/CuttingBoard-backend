package com.revature.cuttingboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.IngredientsDAO;
import com.revature.cuttingboard.dto.IngredientsDTO;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.model.SystemUser;

/**
 * Service class to handle business logic surrounding ingredients
 * @author nom.com
 * @since 1.0
 *
 */
@Service
public class IngredientsService {
	
	@Autowired
	private IngredientsDAO ingredientsDao;
	
	public List<IngredientsDTO> getAllIngredients() throws Exception {
		List<IngredientsDTO> ingredients = convertLists(ingredientsDao.getAllIngredients());
		
		return ingredients;
	}
	
	public List<IngredientsDTO> searchIngredients(String ingredient) throws Exception {
		List<IngredientsDTO> ingredients = convertLists(ingredientsDao.searchIngredient(ingredient));
		
		return ingredients;
	}
	
	public IngredientsDTO getIngredientById(int id) throws Exception {
		return new IngredientsDTO(ingredientsDao.getIngredientById(id));
	}
	
	public IngredientsDTO insertIngredient(Ingredients ingredient, SystemUser user) throws Exception {
		//set values not passed in by the user
		Date today = new Date();
		ingredient.setCreationDate(today);
		ingredient.setLastUpdatDate(today);
		ingredient.setCreatedBy(user);
		ingredient.setLastUpdatedBy(user);
		
		return new IngredientsDTO(ingredientsDao.insertIngredient(ingredient));
	}
	
	public IngredientsDTO updateIngredient(Ingredients ingredient, SystemUser user) throws Exception {
		Date today = new Date();
		ingredient.setLastUpdatDate(today);
		ingredient.setLastUpdatedBy(user);
		
		return new IngredientsDTO(ingredientsDao.updateIngredient(ingredient));
	}
	
	public boolean deleteIngredient(int id) throws Exception {
		return ingredientsDao.deleteIngredient(id);
	}
	
	private List<IngredientsDTO> convertLists(List<Ingredients> ingredientsList) {
		List<IngredientsDTO> ingredients = new ArrayList<IngredientsDTO>();
		for (Ingredients i : ingredientsList) {
			ingredients.add(new IngredientsDTO(i));
		}
		
		return ingredients;
	}
}
