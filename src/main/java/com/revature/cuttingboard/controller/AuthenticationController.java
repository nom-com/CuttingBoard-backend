package com.revature.cuttingboard.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.dao.SystemUserDAO;
import com.revature.cuttingboard.dto.CredentialsDTO;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.AuthenticationService;
import com.revature.cuttingboard.utils.TokenUtility;

@RestController
public class AuthenticationController {
		
		@Autowired
		TokenUtility token;
		@Autowired
		HttpServletResponse resp;
		@Autowired
		AuthenticationService authService;
		@Autowired
		SystemUserDAO systemUserDao;
		
		@PostMapping(value="/login")
		public CredentialsDTO login(@RequestBody CredentialsDTO user) throws Exception {
			try {
				//Fields
				String currentUsername = user.getUsername();
				SystemUser storedUser = (SystemUser)systemUserDao.getUserByUsername(currentUsername);
				String storedId = String.valueOf(storedUser.getId());
				
				//Create and set token for user
				String userToken = token.createJWT(storedId, "Nom.com", currentUsername, 1500000);
				user.setToken(userToken);
				resp.setHeader("Token", user.getToken());
				
				resp.getWriter().append("Welcome " + user.getUsername());
				resp.setStatus(200);
				return authService.login(user);
			} catch (Exception e) {
				resp.setStatus(400);
				return null;
			}
		}
		
		@GetMapping(value="/logout")
		public void logout(String userToken) throws Exception{
			try {
				//Create and set token that expires immediately
				if (token != null) {
					userToken = token.createJWT("0", "Nom.com", "Expired", 0);
					resp.setHeader("Token", userToken);
					resp.getWriter().append("Goodbye");
					resp.setStatus(200);
				}
			} catch (Exception e) {
				resp.setStatus(400);
				e.printStackTrace();
			}
		}
}