package com.revature.cuttingboard.exception;

public class EmailAlreadyExists extends Exception {

	public EmailAlreadyExists() {
		super("Email already exists.");
	}
	
	public EmailAlreadyExists(String message) {
		super(message);
	}
}
