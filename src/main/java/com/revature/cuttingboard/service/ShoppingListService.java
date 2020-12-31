package com.revature.cuttingboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.ShoppingListDAO;
import com.revature.cuttingboard.dto.IngredientsDTO;
import com.revature.cuttingboard.dto.ShoppingListDTO;
import com.revature.cuttingboard.model.ShoppingList;
import com.revature.cuttingboard.model.SystemUser;

/**
 * Service class to handle business logic surrounding shopping lists.
 * @author nom.com
 * @since 1.0
 */

@Service
public class ShoppingListService {

		@Autowired
		ShoppingListDAO shoppingDao;
		
		public ShoppingListDTO insertShoppingList(ShoppingList shoppingList, SystemUser user) throws Exception {
			Date today = new Date();
			shoppingList.setCreationDate(today);
			shoppingList.setSystemUser(user);
			
			return new ShoppingListDTO(shoppingDao.insertShoppingList(shoppingList));
		}
		
		public ShoppingListDTO getShoppingListById(int id) throws Exception {
			return new ShoppingListDTO(shoppingDao.getShoppingListById(id));
		}
		
		public List<ShoppingListDTO> getShoppingListByUserId(SystemUser user) throws Exception {
			return convertLists(shoppingDao.getShoppingListByUserId(user.getId()));
		}
		
		public boolean deleteShoppingList(int id) throws Exception {
			return shoppingDao.deleteShoppingList(id);
		}
		
		private List<ShoppingListDTO> convertLists(List<ShoppingList> dbShoppingList) {
			List<ShoppingListDTO> recipes = new ArrayList<ShoppingListDTO>();

			for (ShoppingList s : dbShoppingList) {
				recipes.add(new ShoppingListDTO(s));
			}

			return recipes;
		}
}
