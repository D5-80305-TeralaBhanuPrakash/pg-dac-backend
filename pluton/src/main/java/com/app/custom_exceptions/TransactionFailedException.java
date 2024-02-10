package com.app.custom_exceptions;

public class TransactionFailedException extends RuntimeException {

	public TransactionFailedException(String message) {
		super(message);
	}
	
}
