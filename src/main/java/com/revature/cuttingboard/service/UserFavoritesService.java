package com.revature.cuttingboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.UserFavoritesDAO;
import com.revature.cuttingboard.dto.RecipeDTO;
import com.revature.cuttingboard.dto.UserFavoritesDTO;
import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.model.UserFavorites;

/**
 * Service class to handle business logic surrounding user favorites.
 * @author nom.com
 * @since 1.0
 */

@Service
public class UserFavoritesService {

	@Autowired
	UserFavoritesDAO userFavsDao;
	@Autowired
	RecipeService recipeService;
	
	public UserFavoritesDTO insertUserFavorites(int id, SystemUser user) throws Exception {
		UserFavorites userFavorites = new UserFavorites();
		Date today = new Date();
		Recipe recipe = new Recipe();
		recipe.setId(id);
		userFavorites.setRecipe(recipe);
		userFavorites.setCreationDate(today);
		userFavorites.setSystemUser(user);
		userFavorites = userFavsDao.insertUserFavorites(userFavorites);
		RecipeDTO recipeDTO = recipeService.getRecipeById(id);
		UserFavoritesDTO userFavsDTO = new UserFavoritesDTO(userFavorites.getId(), recipeDTO);
		
		return userFavsDTO;
	}
	
	public List<UserFavoritesDTO> getUserFavorites(int id) throws Exception {
		List<UserFavorites> favList = userFavsDao.getUserFavorites(id);
		List<UserFavoritesDTO> favDTO = new ArrayList<UserFavoritesDTO>();
		for (UserFavorites u : favList) {
			UserFavoritesDTO ufDTO = new UserFavoritesDTO(u.getId(), recipeService.getRecipeById(u.getRecipe().getId()));
			favDTO.add(ufDTO);
		}
		return favDTO;
	}
	
	public boolean deleteUserFavorites(int id) throws Exception {
		return userFavsDao.deleteUserFavorites(id);
	}
	
}
