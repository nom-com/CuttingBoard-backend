package com.revature.cuttingboard.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.exception.EmailNotFound;
import com.revature.cuttingboard.exception.PSQLException;
import com.revature.cuttingboard.exception.UserNotFound;
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
	
	
	public SystemUser insertUser(SystemUser newUser) throws PSQLException {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			session.save(newUser);
			
			tx.commit();
			session.close();
			return newUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PSQLException();
		}
	}
	
	public SystemUser getUserByUsername(String username) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			String query = "FROM SystemUser s WHERE s.username = :username";
			SystemUser user = (SystemUser) session.createQuery(query).setParameter("username",  username).getSingleResult();
			
			session.close();
			return user;
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new UserNotFound();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL error.");
		}
	}
	
	public SystemUser getUserByEmail(String email) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			String query = "FROM SystemUser s WHERE s.email = :email";
			SystemUser user = (SystemUser) session.createQuery(query).setParameter("email",  email).getSingleResult();
			
			session.close();
			return user;
		} catch(NoResultException e) {
			e.printStackTrace();
			throw new EmailNotFound();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PSQL error.");
		}
	}
}
