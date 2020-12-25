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

import com.revature.cuttingboard.dto.CredentialsDTO;
import com.revature.cuttingboard.utils.TokenUtility;

@RestController
public class AuthenticationController {
		
		@Autowired
		TokenUtility token;
		
		@Autowired
		HttpServletResponse resp;
		
		@PostMapping(value="/login")
		public CredentialsDTO login(@RequestBody CredentialsDTO user) {
			if (user.getUsername().equals("user") && user.getPassword().equals("admin")) {
				String userToken = token.createJWT("1", "Nom.com", user.getUsername(), 20000);
				resp.addHeader("Token", userToken);
				System.out.println(userToken);
				return user;
			} else {
				return null;
			}
		}
		
		@GetMapping(value="/logout")
		public String logout() {
			if (token != null) {
				long nowMillis = System.currentTimeMillis();
				long expMillis = nowMillis * 0;
				Date exp = new Date(expMillis);
				token.setExpiration(exp);
				System.out.println(token.getExpiration());
				return "You logged out.";
			} else {
				return"";
			}
		}
}