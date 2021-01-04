package com.revature.cuttingboard.exception;

public class UsernameAlreadyExists extends Exception {
	
	public UsernameAlreadyExists() {
		super("Username already exists.");
	}

	public UsernameAlreadyExists(String message) {
		super(message);
	}
}
