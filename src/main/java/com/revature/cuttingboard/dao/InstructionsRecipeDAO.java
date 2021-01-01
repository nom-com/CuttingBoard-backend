package com.revature.cuttingboard.dao;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.InstructionsRecipe;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interactions pertaining to the instuctions_recipe table 
 * @author nom.com
 * @since 1.0
 *
 */
@Repository
public class InstructionsRecipeDAO {

	public InstructionsRecipe getInstructionsRecipeById(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			
			InstructionsRecipe instructionsRecipe = session.get(InstructionsRecipe.class, id);
			
			return instructionsRecipe;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public InstructionsRecipe insertInstuctionsRecipe(InstructionsRecipe instructionsRecipe) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			session.save(instructionsRecipe);
			
			tx.commit();
			return instructionsRecipe;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public InstructionsRecipe updateInstructionsRecipe(InstructionsRecipe instructionsRecipe) throws Exception {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			InstructionsRecipe dbInstruction = session.load(InstructionsRecipe.class, instructionsRecipe.getId());
			dbInstruction.getInstruction().setStep(instructionsRecipe.getInstruction().getStep());
			dbInstruction.setLastUpdatedBy(instructionsRecipe.getLastUpdatedBy());
			dbInstruction.setLastUpdateDate(instructionsRecipe.getLastUpdateDate());
			session.update(dbInstruction);
			
			return dbInstruction;
		} catch (ObjectNotFoundException nf) {
			session.save(instructionsRecipe);
			return instructionsRecipe;
		} catch (Exception e) {
			tx.rollback();
			throw new Exception("PSQL Error");
		} finally {
			tx.commit();
			session.close();
		}
	}
	
	public boolean deleteInstructionsRecipe(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			InstructionsRecipe instructionsRecipe = session.get(InstructionsRecipe.class, id);
			session.delete(instructionsRecipe);
			
			tx.commit();
			return true;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
}
