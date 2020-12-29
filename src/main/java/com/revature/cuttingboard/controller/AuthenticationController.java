package com.revature.cuttingboard.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.dto.CredentialsDTO;
import com.revature.cuttingboard.dto.SystemUserDTO;
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
		
		@PostMapping(value="/login")
		public SystemUserDTO login(@RequestBody CredentialsDTO user) throws Exception {
			try {
				SystemUserDTO sysUser = authService.login(user);
				if(sysUser != null) {
					//Fields
					String currentUsername = sysUser.getUsername();
					String storedId = String.valueOf(sysUser.getId());
					
					//Create and set token for user
					String userToken = token.createJWT(storedId, "Nom.com", currentUsername, 200000000);
					resp.setHeader("Token", userToken);
	
					resp.setStatus(200);
					return sysUser;
				} else {
					resp.setStatus(400);
					resp.getWriter().append("Incorrect password.");
					return null;
				}
			} catch (Exception e) {
				resp.setStatus(400);
				return null;
			}
		}
}