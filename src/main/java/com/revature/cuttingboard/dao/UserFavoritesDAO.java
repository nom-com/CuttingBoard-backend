package com.revature.cuttingboard.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.UserFavorites;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions pertaining to user favorites.
 * @author nom.com
 * @since 1.0
 */

@Repository
public class UserFavoritesDAO {
	
	@SuppressWarnings("unchecked")
	public List<UserFavorites> getUserFavorites(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			String query = "FROM UserFavorites u WHERE u.systemUser.id = :id";
			List<UserFavorites> shoppingList = (List<UserFavorites>) session.createQuery(query).setParameter("id",  id).list();
			tx.commit();
			return shoppingList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	} 
	
	@SuppressWarnings("unchecked")
	public List<UserFavorites> getUserFavoritesByRecipeId(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			String query = "FROM UserFavorites u WHERE u.recipe.id = :id";
			List<UserFavorites> shoppingList = (List<UserFavorites>) session.createQuery(query).setParameter("id",  id).list();
			tx.commit();
			return shoppingList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	} 
	
	public UserFavorites insertUserFavorites(UserFavorites userFavorites) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(userFavorites);
			tx.commit();
			session.close();
			return userFavorites;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL Error");
		}
	}
	
	public boolean deleteUserFavorites(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			UserFavorites userFavorites = session.get(UserFavorites.class, id);
			session.delete(userFavorites);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean deleteUserFavoritesByRecipe(int id, int userId) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			UserFavorites userFavorites = (UserFavorites) session.createQuery("FROM UserFavorites u WHERE u.recipe.id = :rid AND u.systemUser.id = :uid")
					.setParameter("rid", id)
					.setParameter("uid", userId)
					.getSingleResult();
			session.delete(userFavorites);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
