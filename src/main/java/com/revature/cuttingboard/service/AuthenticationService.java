package com.revature.cuttingboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.SystemUserDAO;
import com.revature.cuttingboard.dto.CredentialsDTO;
import com.revature.cuttingboard.dto.SystemUserDTO;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.PasswordHashingUtility;

/**
 * Service class to handle user authentication.
 * @author nom.com
 */

@Service
public class AuthenticationService {

	@Autowired
	SystemUserDAO systemUserDao;
	@Autowired
	PasswordHashingUtility passHash;
	
	public SystemUserDTO login(CredentialsDTO user) throws Exception {
		try {
			//Fields 
			String currentUsername = user.getUsername();
			String currentPassword = user.getPassword();
			SystemUser storedUser = (SystemUser)systemUserDao.getUserByUsername(currentUsername);
			String storedPassword = storedUser.getPassword();
			String storedSalt = storedUser.getSalt();
			
			//Validate password and return a user
			if (passHash.validatePassword(currentPassword, storedPassword, storedSalt)) {
				return new SystemUserDTO(storedUser);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("User not found.");
		}
	}
}
