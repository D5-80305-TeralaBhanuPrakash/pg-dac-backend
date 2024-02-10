package com.app.custom_exceptions;

public class TransactionsNotFoundException extends RuntimeException {

	public TransactionsNotFoundException(String message) {
		super(message);
	}

}
