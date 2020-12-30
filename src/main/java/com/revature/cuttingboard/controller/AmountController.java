package com.revature.cuttingboard.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.annotation.AuthenticatedEndpoint;
import com.revature.cuttingboard.dto.AmountDTO;
import com.revature.cuttingboard.dto.AmountDataDTO;
import com.revature.cuttingboard.model.Amount;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.AmountService;

/**
 * Controller class to handle Http requests dealing with amounts.
 * @author nom.com
 * @since 1.0
 *
 */
@RestController
@RequestMapping("/amount")
public class AmountController {

	@Autowired
	private AmountService amountService;
	@Autowired
	private HttpServletResponse res;
	
	@GetMapping("/{id}")
	@AuthenticatedEndpoint
	public AmountDTO getAmountById(@PathVariable("id") int id, SystemUser user) {
		try {
			return amountService.getAmountById(id);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("")
	@AuthenticatedEndpoint
	public AmountDTO insertAmount(@RequestBody AmountDataDTO amount, SystemUser user) {
		try {
			res.setStatus(201);
			return amountService.insertAmount(amount, user);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/{id}")
	@AuthenticatedEndpoint
	public AmountDTO updateAmount(@PathVariable("id") int id, @RequestBody Amount amountData, SystemUser user) {
		amountData.setId(id);
		try {
			return amountService.updateAmount(amountData, user);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	@AuthenticatedEndpoint
	public void deleteAmount(@PathVariable int id, SystemUser user) {
		try {
			if (amountService.deleteAmount(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(400);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
}
