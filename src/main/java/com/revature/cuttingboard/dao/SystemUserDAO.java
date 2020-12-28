package com.revature.cuttingboard.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions for requests dealing with system users. 
 * @author nom.com
 * @since 1.0
 *
 */

@Repository
public class SystemUserDAO {
	

	public SystemUser insertUser(SystemUser newUser) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(newUser);
			
			tx.commit();
			session.close();
			return newUser;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public SystemUser getUserByUsername(String username) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			String query = "FROM SystemUser s WHERE s.username = :username";
			SystemUser user = (SystemUser) session.createQuery(query).setParameter("username",  username).getSingleResult();
			
			session.close();
			return user;
		} catch (Exception e) {
			throw new Exception("User not found.");
		}
	}
}
