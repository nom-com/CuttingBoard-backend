package com.revature.cuttingboard.exception;

public class EmailNotFound extends Exception {

	public EmailNotFound() {
		super("Email not found.");
	}
	
	public EmailNotFound(String message) {
		super(message);
	}
}
