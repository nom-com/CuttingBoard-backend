package com.revature.cuttingboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cuttingboard.dao.InstructionsDAO;

@Service
public class InstructionsService {

		@Autowired
		InstructionsDAO instructionsDAO;
		
		
}
