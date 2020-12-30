package com.revature.cuttingboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.CategoryDAO;
import com.revature.cuttingboard.dto.CategoryDTO;
import com.revature.cuttingboard.model.Category;
import com.revature.cuttingboard.model.SystemUser;

/**
 * Service class to handle business logic pertaining to categories
 * @author nom.com
 * @since 1.0
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDao;
	
	public CategoryDTO insertCategory(Category category, SystemUser user) throws Exception {
		//set values 
		Date today = new Date();
		category.setCreationDate(today);
		category.setCreatedBy(user);
		
		//Insert categories
		return new CategoryDTO(categoryDao.insertCategory(category));
	}
	
	public List<CategoryDTO> getAllCategories() throws Exception{
		List<CategoryDTO> category = convertLists(categoryDao.getAllCategories());
		
		return category;
	}
	
	public CategoryDTO getCategoryById(int id) throws Exception {
		return new CategoryDTO(categoryDao.getCategoryById(id));
	}
	
	public List<CategoryDTO> searchCategories(String category) throws Exception {
		List<CategoryDTO> categories = convertLists(categoryDao.searchCategory(category));
		
		return categories;
	}
	
	public boolean deleteCategory(int id) throws Exception {
		return categoryDao.deleteCategory(id);
	}
	
	private List<CategoryDTO> convertLists(List<Category> categoryList) {
		//Convert items in a list of type Category to 
		//a list of type CategoryDTO
		List<CategoryDTO> category = new ArrayList<CategoryDTO>();
		for (Category i : categoryList) {
			category.add(new CategoryDTO(i));
		}
		
		return category;
	}
}
