package com.revature.cuttingboard.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.AmountDAO;
import com.revature.cuttingboard.dto.AmountDTO;
import com.revature.cuttingboard.dto.AmountDataDTO;
import com.revature.cuttingboard.model.Amount;
import com.revature.cuttingboard.model.Ingredients;
import com.revature.cuttingboard.model.SystemUser;

/**
 * Service class to handle business logic pertaining to ingredient amounts
 * @author nom.com
 * @since 1.0
 *
 */
@Service
public class AmountService {
	
	@Autowired
	private AmountDAO amountDao;
	
	public AmountDTO getAmountById(int id) throws Exception {
		return new AmountDTO(amountDao.getAmountById(id));
	}
	
	public AmountDTO insertAmount(AmountDataDTO amountData, SystemUser user) throws Exception {
		Date today = new Date();
		Ingredients ingredient = new Ingredients();
		ingredient.setId(amountData.getIngredientId());
		Amount amount = new Amount(amountData.getAmount(),ingredient, user, today, user, today);
		
		return new AmountDTO(amountDao.insertAmount(amount));
	}
	
	public AmountDTO updateAmount(Amount amountData, SystemUser user) throws Exception {
		Date today = new Date();
		amountData.setLastUpdateDate(today);
		amountData.setLastUpdatedBy(user);
		
		return new AmountDTO(amountDao.updateAmount(amountData));
	}
	
	public boolean deleteAmount(int id) throws Exception {
		return amountDao.deleteAmount(id);
	}
}
