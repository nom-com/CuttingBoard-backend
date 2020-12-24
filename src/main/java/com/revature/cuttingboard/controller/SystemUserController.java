package com.revature.cuttingboard.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.dto.SystemUserDTO;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.SystemUserService;

@RestController
@RequestMapping("/user")
public class SystemUserController {

	@Autowired
	private SystemUserService systemUserService;
	
	@PostMapping("/create")
	public SystemUserDTO createUser(@RequestBody SystemUser newUser, HttpServletResponse res) {
		try {
			res.setStatus(201);
			return systemUserService.createUser(newUser);
		} catch (Exception e) {
			res.setStatus(400);
			return null;
		}
	}
}
