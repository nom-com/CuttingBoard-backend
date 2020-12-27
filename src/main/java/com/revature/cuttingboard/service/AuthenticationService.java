package com.revature.cuttingboard.service;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.cuttingboard.dao.SystemUserDAO;
import com.revature.cuttingboard.dto.CredentialsDTO;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.utils.PasswordHashingUtility;
import com.revature.cuttingboard.utils.TokenUtility;

/**
 * Service class to handle user authentication for users.
 * @author nom.com
 */

@Service
public class AuthenticationService {

	@Autowired
	SystemUserDAO systemUserDao;
	@Autowired
	TokenUtility token;
	@Autowired
	PasswordHashingUtility passHash;
	@Autowired
	HttpServletResponse resp;
	
	public CredentialsDTO login(CredentialsDTO user) throws Exception {
		try {
			//Fields 
			String currentUsername = user.getUsername();
			String currentPassword = user.getPassword();
			SystemUser storedUser = (SystemUser)systemUserDao.getUserByUsername(currentUsername);
			String storedId = String.valueOf(storedUser.getId());
			String storedUsername = storedUser.getUsername();
			String storedPassword = storedUser.getPassword();
			String storedSalt = storedUser.getSalt();
			
			//Validate password and return a user
			if (currentUsername.equals(storedUsername) && currentPassword.equals(storedPassword)) {
				passHash.validatePassword(currentPassword, storedPassword, storedSalt);
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception("User not found."); 
		}
	}
	
//	public TokenUtility logout() throws Exception {
//		try {
//			//Set token expiration to 0
//			long nowMillis = System.currentTimeMillis();
//			long expMillis = nowMillis * 0;
//			Date exp = new Date(expMillis);
//			token.setExpiration(exp);
//			return token;
//		} catch (Exception e) {
//			throw new Exception("No token available.");
//		}
//	}
}
