package com.revature.cuttingboard.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cuttingboard.annotation.AuthenticatedEndpoint;
import com.revature.cuttingboard.dto.InstructionsDTO;
import com.revature.cuttingboard.model.Instructions;
import com.revature.cuttingboard.model.SystemUser;
import com.revature.cuttingboard.service.InstructionsService;

@RestController
@RequestMapping("/instructions")
public class InstructionsController {

		@Autowired
		private InstructionsService iService;
		@Autowired 
		HttpServletResponse resp;
		
		
		@PostMapping("/create")
		@AuthenticatedEndpoint
		public InstructionsDTO createInstruction(@RequestBody Instructions instructions, SystemUser user) throws Exception {
			try {
				resp.setStatus(201);
				return iService.createInstructions(instructions, user);
			} catch (Exception e) {
				resp.setStatus(400);
				e.printStackTrace();
				return null;
			}
		}
}
