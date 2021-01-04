package com.revature.cuttingboard.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.SystemUserDAO;
import com.revature.cuttingboard.dto.SystemUserDTO;
import com.revature.cuttingboard.exception.EmailAlreadyExists;
import com.revature.cuttingboard.exception.EmailNotFound;
import com.revature.cuttingboard.exception.UsernameAlreadyExists;
import com.revature.cuttingboard.exception.UserNotFound;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.PasswordHashingUtility;

/**
 * Service class to handle business logic for requests pertaining to system users.
 * @author nom.com
 * @since 1.0
 *
 */

@Service
public class SystemUserService {

	@Autowired
	private SystemUserDAO systemUserDao;
	@Autowired
	private PasswordHashingUtility passwordHashingUtility;
	
	
	public SystemUserDTO createUser(SystemUser newUser) throws Exception {
		checkUsername(newUser.getUsername());
		checkEmail(newUser.getEmail());
		// setting common values
		Date today = new Date();
		newUser.setCreationDate(today);
		newUser.setLastUpdateDate(today);
		newUser.setAdmin(false);
		
		// password hashing
		newUser.setSalt(passwordHashingUtility.getSalt());
		String hashedPassword = passwordHashingUtility.generateHash(newUser.getPassword(), newUser.getSalt().getBytes());
		newUser.setPassword(hashedPassword);
		
		//insert user
		SystemUser user = systemUserDao.insertUser(newUser);
		return new SystemUserDTO(user);
	}
	
	//Checks for existing username
	private boolean checkUsername(String user) throws Exception {
		try {
			systemUserDao.getUserByUsername(user);
			throw new UsernameAlreadyExists();
		} catch (UserNotFound e) {
			return true;
		}
		
	}
	
	//Checks for existing email
	private boolean checkEmail(String user) throws Exception {
		try {
			SystemUser dbEmail = systemUserDao.getUserByEmail(user);
			throw new EmailAlreadyExists();
		} catch (EmailNotFound e) {
			return true;
		}
		
	}
}
