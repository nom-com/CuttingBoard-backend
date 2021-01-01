package com.revature.cuttingboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.annotation.AuthenticatedEndpoint;
import com.revature.cuttingboard.dto.RecipeDTO;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.RecipeService;

/**
 * Controller class to handle Http requests dealing with recipes
 * @author nom.com
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private HttpServletResponse res;
	
	@GetMapping("")
	@AuthenticatedEndpoint
	public List<RecipeDTO> getAllRecipes(SystemUser user){
		try {
			return recipeService.getAllRecipes();
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/recommended")	
	public List<RecipeDTO> getRecommendedRecipes() {
		try {
			return recipeService.getRecommendedRecipes();
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/id/{id}")
	@AuthenticatedEndpoint
	public RecipeDTO getRecipeById(@PathVariable("id") int id, SystemUser user) {
		try {
			return recipeService.getRecipeById(id);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/{search}")
	@AuthenticatedEndpoint
	public List<RecipeDTO> searchRecipes(@PathVariable("search") String search, SystemUser user) {
		try {
			return recipeService.searchRecipes(search);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/category/{id}")
	@AuthenticatedEndpoint
	public List<RecipeDTO> searchRecipesByCategory(@PathVariable("id") int id, SystemUser user) {
		try {
			return recipeService.searchRecipesByCategory(id);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("")
	@AuthenticatedEndpoint
	public RecipeDTO insertRecipe(@RequestBody RecipeDTO recipeData, SystemUser user) {
		try {
			res.setStatus(201);
			return recipeService.insertRecipe(recipeData, user);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/{id}")
	@AuthenticatedEndpoint
	public RecipeDTO updateRecipe(@PathVariable("id") int id, @RequestBody RecipeDTO recipeData, SystemUser user) {
		try {
			return recipeService.updateRecipe(recipeData, id, user);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	@AuthenticatedEndpoint
	public void deleteRecipe(@PathVariable("id") int id, SystemUser user) {
		if (recipeService.deleteRecipe(id)) {
			res.setStatus(204);
		} else {
			res.setStatus(400);
		}
	}
}
