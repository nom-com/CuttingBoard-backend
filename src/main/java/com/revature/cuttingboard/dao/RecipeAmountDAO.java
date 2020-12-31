package com.revature.cuttingboard.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.RecipeAmount;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions pertaining to the recipe_amount table
 * @author nom.com
 * @since 1.0
 *
 */
@Repository
public class RecipeAmountDAO {

	public RecipeAmount getRecipeAmountById(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			
			RecipeAmount recipeAmount = session.get(RecipeAmount.class, id);
			
			return recipeAmount;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public RecipeAmount insertRecipeAmount(RecipeAmount recipeAmount) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			session.save(recipeAmount);
			
			tx.commit();
			return recipeAmount;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public boolean deleteRecipeAmount(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			RecipeAmount recipeAmount = session.get(RecipeAmount.class, id);
			session.delete(recipeAmount);
			
			tx.commit();
			return true;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
}
