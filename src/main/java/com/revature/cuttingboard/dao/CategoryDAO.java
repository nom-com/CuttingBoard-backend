package com.revature.cuttingboard.dao;

import java.util.List;

import org.hibernate.Session;
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

	public List<Category> getAllCategories() throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			List<Category> categories = (List<Category>) session.createQuery("From Category c").list();
			
			session.close();
			return categories;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
}
