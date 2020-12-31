package com.revature.cuttingboard.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.ShoppingList;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions pertaining to shopping lists.
 * @author nom.com
 * @since 1.0
 */

@Repository
public class ShoppingListDAO {
		
		public ShoppingList getShoppingListById(int id) throws Exception {
			try (Session session = HibernateUtility.getSession()) {
				Transaction tx = session.beginTransaction();
				
				ShoppingList shoppingList = session.get(ShoppingList.class, id);
				
				tx.commit();
				return shoppingList;
			} catch (Exception e) {
				throw new Exception("PSQL Error");
			}
		}
		
		public List<ShoppingList> getShoppingListByUserId(int id) throws Exception {
			try (Session session = HibernateUtility.getSession()) {
				Transaction tx = session.beginTransaction();

				String query = "FROM ShoppingList s WHERE s.systemUser.id = :id";
				List<ShoppingList> shoppingList = (List<ShoppingList>) session.createQuery(query).setParameter("id",  id).list();
				
				tx.commit();
				return shoppingList;
			} catch (Exception e) {
				throw new Exception("PSQL Error");
			}
		}
		
		public ShoppingList insertShoppingList(ShoppingList shoppingList) throws Exception {
			try (Session session = HibernateUtility.getSession()) {
				Transaction tx = session.beginTransaction();
				
				session.save(shoppingList);
				
				tx.commit();
				session.close();
				return shoppingList;
			} catch (Exception e) {
				throw new Exception("PSQL Error");
			}
		}
		
		public boolean deleteShoppingList(int id) throws Exception {
			try (Session session = HibernateUtility.getSession()) {
				Transaction tx = session.beginTransaction();
				
				ShoppingList shoppingList = session.get(ShoppingList.class, id);
				session.delete(shoppingList);
				tx.commit();
				return true;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
}
