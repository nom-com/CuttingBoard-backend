package com.revature.cuttingboard.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.InstructionsDAO;
import com.revature.cuttingboard.dao.SystemUserDAO;
import com.revature.cuttingboard.dto.InstructionsDTO;
import com.revature.cuttingboard.model.Instructions;
import com.revature.cuttingboard.model.SystemUser;

/**
 * Handles business logic for requests pertaining to recipe instructions.
 * @author nom.com
 * @since 1.0
 */

@Service
public class InstructionsService {

		@Autowired
		InstructionsDAO instructionsDao;
		@Autowired
		SystemUserDAO systemUserDao;
		
		public InstructionsDTO createInstructions(Instructions instructions, SystemUser user) throws Exception {
			//Set values
			Date today = new Date();
			instructions.setCreationDate(today);
			instructions.setLast_update_date(today);
			instructions.setCreatedBy(user);
			instructions.setLastUpdatedBy(user);
			
			//Insert instructions
			Instructions instruction = instructionsDao.createInstructions(instructions);
			return new InstructionsDTO(instruction);
		}
}
