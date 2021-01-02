package com.revature.cuttingboard.exception;

public class UserAlreadyExists extends Exception {
	
	public UserAlreadyExists() {
		super("User already exists.");
	}

	public UserAlreadyExists(String message) {
		super(message);
	}
}
