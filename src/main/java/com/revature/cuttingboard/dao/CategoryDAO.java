package com.revature.cuttingboard.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.Category;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions for the category table in the database. 
 * @author nom.com
 * @since 1.0
 *
 */
@Repository
public class CategoryDAO {

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			List<Category> categories = (List<Category>) session.createQuery("From Category c").list();
			
			session.close();
			return categories;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Category getCategoryById(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			Category category = session.get(Category.class, id);
			
			tx.commit();
			session.close();
			return category;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Category insertCategory(Category category) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			session.save(category);
			
			tx.commit();
			session.close();
			return category;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> searchCategory(String category) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			List<Category> categories = (List<Category>)session.createQuery("FROM Category c WHERE LOWER(c.category) like LOWER(:category)")
					.setParameter("category", "%" + category + "%")
					.list();
			
			tx.commit();
			session.close();
			return categories;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public boolean deleteCategory(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			Category category = session.get(Category.class, id);
			session.delete(category);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
