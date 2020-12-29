package com.revature.cuttingboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.CategoryDAO;

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
	
	
}
