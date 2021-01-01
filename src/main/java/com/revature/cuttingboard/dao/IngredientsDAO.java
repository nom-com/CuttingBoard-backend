package com.revature.cuttingboard.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions pertaining to ingredients
 * @author nom.com
 * @since 1.0
 *
 */
@Repository
public class IngredientsDAO {

	public List<Ingredients> getAllIngredients() throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			CriteriaQuery<Ingredients> query = session.getCriteriaBuilder().createQuery(Ingredients.class);
			Root<Ingredients> root = query.from(Ingredients.class);
			query.select(root);
			
			List<Ingredients> ingredients = session.createQuery(query).getResultList();
			
			tx.commit();
			return ingredients;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingredients> searchIngredient(String ingredient) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			List<Ingredients> ingredients = session.createQuery("FROM Ingredients i WHERE LOWER(i.ingredient) like LOWER(:ingredient)")
					.setParameter("ingredient", "%" + ingredient + "%")
					.list();
			
			tx.commit();
			return ingredients;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Ingredients getIngredientById(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			Ingredients ingredient = session.get(Ingredients.class, id);
			
			tx.commit();
			return ingredient;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Ingredients insertIngredient(Ingredients ingredient) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			session.save(ingredient);
			
			tx.commit();
			return ingredient;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Ingredients updateIngredient(Ingredients ingredient) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			Ingredients dbIngredient = session.load(Ingredients.class, ingredient.getId());
			dbIngredient.setIngredient(ingredient.getIngredient());
			dbIngredient.setLastUpdatDate(ingredient.getLastUpdateDate());
			dbIngredient.setLastUpdatedBy(ingredient.getLastUpdatedBy());
			session.update(dbIngredient);
			
			tx.commit();
			return ingredient;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public boolean deleteIngredient(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			Ingredients ingredient = session.get(Ingredients.class, id);
			session.delete(ingredient);
			tx.commit();
			return true;
		} catch (Exception e) {
//			throw new Exception("PSQL Error");
			throw new Exception(e.getMessage());
		}
	}
}
