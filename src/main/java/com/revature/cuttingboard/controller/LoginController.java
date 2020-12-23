package com.revature.cuttingboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.utils.TokenUtility;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/login")
public class LoginController {
		
		TokenUtility token = new TokenUtility();
		
		@PostMapping(value="")
		public String login(@RequestBody String username, String password ) {
			if (username.equals("user") && password.equals("admin")) {
				token.createJWT("1", "Nom.com", "testToken", 2000);
				Claims userToken = token.decodeJWT("secret");
				return username + password + userToken;
			} else {
				return null;
			}
		}
}