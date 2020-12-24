package com.revature.cuttingboard.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.dto.CredentialsDTO;
import com.revature.cuttingboard.utils.TokenUtility;

@RestController
public class LoginController {
		
		@Autowired
		TokenUtility token;
		
		@Autowired
		HttpServletResponse resp;
		
		@PostMapping(value="/login")
		public CredentialsDTO login(@RequestBody CredentialsDTO user) {
			if (user.getUsername().equals("user") && user.getPassword().equals("admin")) {
				String userToken = token.createJWT("1", "Nom.com", user.getUsername(), 2000000);
				resp.addHeader("Token", userToken);
				return user;
			} else {
				return null;
			}
		}
		
		@PostMapping(value="/logout")
}