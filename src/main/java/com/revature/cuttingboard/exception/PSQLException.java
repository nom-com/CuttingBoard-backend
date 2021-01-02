package com.revature.cuttingboard.exception;

public class PSQLException extends Exception {

	public PSQLException() {
		super("PSQL Error.");
	}
	
	public PSQLException(String message) {
		super(message);
	}
}
