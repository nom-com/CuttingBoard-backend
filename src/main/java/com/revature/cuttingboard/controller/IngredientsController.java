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
import com.revature.cuttingboard.dto.IngredientsDTO;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.IngredientsService;

/**
 * Controller class to handle http requests dealing with ingredients
 * @author nom.com
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientsService ingredientsService;
	@Autowired
	private HttpServletResponse res;
	
	@GetMapping("")
	@AuthenticatedEndpoint
	public List<IngredientsDTO> getAllIngredients(SystemUser user) {
		try {
			return ingredientsService.getAllIngredients();
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/{search}")
	@AuthenticatedEndpoint
	public List<IngredientsDTO> searchIngredients(@PathVariable("search") String search,SystemUser user) {
		try {
			return ingredientsService.searchIngredients(search);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/id/{id}")
	@AuthenticatedEndpoint
	public IngredientsDTO getIngredientById(@PathVariable("id") int id, SystemUser user) {
		try {
			return ingredientsService.getIngredientById(id);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("")
	@AuthenticatedEndpoint
	public IngredientsDTO insertIngredient(@RequestBody Ingredients ingredient, SystemUser user) {
		try {
			return ingredientsService.insertIngredient(ingredient, user);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/{id}")
	@AuthenticatedEndpoint
	public IngredientsDTO updateIngredient(@PathVariable("id") int id, @RequestBody Ingredients ingredient, SystemUser user) {
		ingredient.setId(id);
		
		try {
			return ingredientsService.updateIngredient(ingredient, user);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	@AuthenticatedEndpoint
	public void deleteIngredient(@PathVariable("id") int id, SystemUser user) {
		try {
			if (ingredientsService.deleteIngredient(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
}
