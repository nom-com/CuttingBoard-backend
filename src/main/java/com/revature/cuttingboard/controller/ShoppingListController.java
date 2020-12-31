package com.revature.cuttingboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.annotation.AuthenticatedEndpoint;
import com.revature.cuttingboard.dto.ShoppingListDTO;
import com.revature.cuttingboard.model.ShoppingList;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.ShoppingListService;

@RestController
@RequestMapping("/shoppinglist")
public class ShoppingListController {

	@Autowired
	ShoppingListService shoppingService;
	@Autowired
	HttpServletResponse resp;
	
	@PostMapping("")
	@AuthenticatedEndpoint
	public ShoppingListDTO insertShoppingList(@RequestBody ShoppingList shoppingList, SystemUser user) {
		try {
			resp.setStatus(200);
			return shoppingService.insertShoppingList(shoppingList, user);
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/id/{id}")
	@AuthenticatedEndpoint
	public ShoppingListDTO getShoppingListById(@PathVariable("id") int id, SystemUser user) {
		try {
			resp.setStatus(200);
			return shoppingService.getShoppingListById(id);
		} catch(Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("")
	@AuthenticatedEndpoint
	public List<ShoppingListDTO> getShoppingListByUserId(SystemUser user) {
		try {
			resp.setStatus(200);
			return shoppingService.getShoppingListByUserId(user);
		} catch(Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	@AuthenticatedEndpoint
	public void deleteShoppingList(@PathVariable("id") int id, SystemUser user) {
		try {
			resp.setStatus(204);
			shoppingService.deleteShoppingList(id);
		} catch(Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
	}
}
