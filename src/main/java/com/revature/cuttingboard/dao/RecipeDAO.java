package com.revature.cuttingboard.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.utils.HibernateUtility;

@Repository
public class RecipeDAO {

		public Recipe addRecipe(Recipe recipe) throws Exception {
			try (Session session = HibernateUtility.getSession()) {
				Transaction tx = session.beginTransaction();
				session.save(recipe);
				
				tx.commit();
				session.close();
				return recipe;
			} catch (Exception e) {
				throw new Exception("PSQL Error");
			}
		}
}
