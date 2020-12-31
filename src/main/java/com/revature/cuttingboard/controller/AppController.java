package com.revature.cuttingboard.controller;

import java.util.Date;

import org.hibernate.Session;

import com.revature.cuttingboard.model.Amount;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.model.RecipeAmount;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.HibernateUtility;

public class AppController {
	public static void main(String args[]) {
		Session session = HibernateUtility.getSession();
		Date today = new Date();
		SystemUser user = new SystemUser();
		user.setId(1);
		RecipeAmount amount = new RecipeAmount();
		Amount a = new  Amount();
		a.setAmount("1 clove");
		a.setCreatedBy(user);
		a.setCreationDate(today);
		a.setLastUpdatedBy(user);
		a.setLastUpdateDate(today);
		Ingredients i = new Ingredients();
		i.setId(1);
		a.setIngredient(i);
		amount.setAmount(a);
		amount.setCreatedBy(user);
		amount.setCreationDate(today);
		amount.setLastUpdatedBy(user);
		amount.setLastUpdateDate(today);
		Recipe r = new Recipe();
		r.setId(1);
		amount.setRecipe(r);
		
		session.save(amount);
		
		
		System.out.println(amount);
		
		session.close();
	}
}
