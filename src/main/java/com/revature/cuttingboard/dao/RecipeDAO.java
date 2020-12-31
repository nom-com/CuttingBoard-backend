package com.revature.cuttingboard.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.Recipe;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions pertaining to the recipe table
 * 
 * @author nom.com
 * @since 1.0
 *
 */
@Repository
public class RecipeDAO {

	public List<Recipe> getAllRecipes() throws Exception {
		try (Session session = HibernateUtility.getSession()) {

			List<Recipe> recipes = session.createQuery("Select distinct r FROM Recipe r join fetch r.ingredients WHERE r.publicRecipe is true").getResultList();
			recipes = session.createQuery("Select distinct r FROM Recipe r join fetch r.instructions WHERE r.publicRecipe is true").getResultList();

			return recipes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	}

	public Recipe getRecipeById(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {

			Recipe recipe = (Recipe) session.createQuery("Select distinct r FROM Recipe r left join fetch r.ingredients WHERE r.id = :id") 
					.setParameter("id", id)
					.getSingleResult();
			recipe = (Recipe) session.createQuery("Select distinct r FROM Recipe r left join fetch r.instructions WHERE r.id = :id") 
					.setParameter("id", id)
					.getSingleResult();

			return recipe;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	}
	
	public List<Recipe> searchRecipe(String search) throws Exception {
		try (Session session = HibernateUtility.getSession()) {

			List<Recipe> recipe = session.createQuery("Select distinct r FROM Recipe r left join fetch r.ingredients "
					+ "WHERE (LOWER(r.title) like LOWER(:search) OR LOWER(r.description) like LOWER(:desc) OR LOWER(r.category.category) LIKE LOWER(:cat)) AND r.publicRecipe is true")
					.setParameter("search", "%" + search + "%")
					.setParameter("desc", "%" + search + "%")
					.setParameter("cat", "%" + search + "%")
					.getResultList();
			recipe = session.createQuery("Select distinct r FROM Recipe r left join fetch r.instructions "
					+ "WHERE (LOWER(r.title) like LOWER(:search) OR LOWER(r.description) like LOWER(:desc)OR LOWER(r.category.category) LIKE LOWER(:cat)) AND r.publicRecipe is true") 
					.setParameter("search", "%" + search + "%")
					.setParameter("desc", "%" + search + "%")
					.setParameter("cat", "%" + search + "%")
					.getResultList();

			return recipe;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	}
	
	public List<Recipe> searchRecipesByCategory(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {

			List<Recipe> recipe = session.createQuery("Select distinct r FROM Recipe r left join fetch r.ingredients WHERE r.category.id = :id AND r.publicRecipe is true") 
					.setParameter("id", id)
					.getResultList();
			recipe = session.createQuery("Select distinct r FROM Recipe r left join fetch r.instructions WHERE r.category.id = :id AND r.publicRecipe is true") 
					.setParameter("id", id)
					.getResultList();

			return recipe;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	}

	public Recipe insertRecipe(Recipe recipe) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();

			session.save(recipe);

			tx.commit();
			return recipe;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Recipe updateRecipe(Recipe recipe) throws Exception{
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();

			session.update(recipe);

			tx.commit();
			return recipe;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public boolean deleteRecipe(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();

			Recipe recipe = session.get(Recipe.class, id);
			session.delete(recipe);

			tx.commit();
			return true;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
}
