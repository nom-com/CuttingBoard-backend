package com.revature.cuttingboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.annotation.AuthenticatedEndpoint;
import com.revature.cuttingboard.dto.CategoryDTO;
import com.revature.cuttingboard.model.Category;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.CategoryService;

/**
 * Controller class to handle HTTP requests dealing with categories
 * @author nom.com
 * @since 1.0
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService catService;
	@Autowired
	HttpServletResponse resp;
	
	@PostMapping("")
	@AuthenticatedEndpoint
	public CategoryDTO insertCategory(@RequestBody Category category, SystemUser user) {
		try {
			resp.setStatus(201);
			return catService.insertCategory(category, user);
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("")
	@AuthenticatedEndpoint
	public List<CategoryDTO> getAllCategories(SystemUser user) {
		try {
			resp.setStatus(200);
			return catService.getAllCategories();
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/{search}")
	@AuthenticatedEndpoint
	public List<CategoryDTO> searchCategories(@PathVariable("search") String search, SystemUser user) {
		try {
			resp.setStatus(200);
			return catService.searchCategories(search);
		} catch(Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/id/{id}")
	@AuthenticatedEndpoint
	public CategoryDTO searchCategoryById(@PathVariable("id") int id, SystemUser user) {
		try {
			resp.setStatus(200);
			return catService.getCategoryById(id);
		} catch(Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
}
