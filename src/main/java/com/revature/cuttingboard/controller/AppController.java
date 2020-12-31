package com.revature.cuttingboard.controller;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.cuttingboard.model.Amount;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.model.RecipeAmount;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.HibernateUtility;

public class AppController {
	public static void main(String args[]) {
		Date today = new Date();
		SystemUser user = new SystemUser();
		user.setId(1);
		Recipe recipe = new Recipe();
		recipe.setId(9);
		Ingredients i = new Ingredients();
		i.setId(1);
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
//		RecipeAmount amt = new RecipeAmount(recipe, new Amount("Some", i, user, today, user, today), user, today, user, today);
//		session.save(amt);
		
		RecipeAmount ra = session.load(RecipeAmount.class, 29);
		session.delete(ra);
		
		
		tx.commit();
		session.close();
		
	}
}
