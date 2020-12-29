package com.revature.cuttingboard.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.Instructions;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions for requests dealing with instructions
 * for recipes.
 * @author nom.com
 *
 */

@Repository
public class InstructionsDAO {

		public Instructions createInstructions (Instructions instructions) throws Exception {
			try (Session session = HibernateUtility.getSession()) {
				Transaction tx = session.beginTransaction();
				session.save(instructions);
				
				tx.commit();
				session.close();
				return instructions;
			} catch (Exception e) {
				throw new Exception("PSQL Error");
			}
		}
}
