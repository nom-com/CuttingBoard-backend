package com.revature.cuttingboard.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.cuttingboard.model.Amount;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.utils.HibernateUtility;

/**
 * DAO class to handle database interaction dealing with the amount table of the database
 * @author nom.com
 * @since 1.0
 *
 */
@Repository
public class AmountDAO {
	
	public Amount getAmountById(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()){
			return session.get(Amount.class, id);
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}

	public Amount insertAmount(Amount amount) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			session.save(amount);
			Ingredients ingredient = session.get(Ingredients.class, amount.getIngredient().getId());
			amount.setIngredient(ingredient);
			
			tx.commit();
			return amount;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public Amount updateAmount(Amount amount) throws Exception {
		try (Session session = HibernateUtility.getSession()) {
			Transaction tx = session.beginTransaction();
			
			Amount dbAmount = session.load(Amount.class, amount.getId());
			dbAmount.setAmount(amount.getAmount());
			dbAmount.setLastUpdatedBy(amount.getLastUpdatedBy());
			dbAmount.setLastUpdateDate(amount.getLastUpdateDate());
			
			session.update(dbAmount);
			
			tx.commit();
			return dbAmount;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
	
	public boolean deleteAmount(int id) throws Exception {
		try (Session session = HibernateUtility.getSession()){
			Transaction tx = session.beginTransaction();
			Amount amount = session.load(Amount.class, id);
			session.delete(amount);
			tx.commit();
			return true;
		} catch (Exception e) {
			throw new Exception("PSQL Error");
		}
	}
}
