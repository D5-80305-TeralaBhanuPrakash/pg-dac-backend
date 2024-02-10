package com.app.custom_exceptions;

public class CustomerAlreadyRegisteredException extends RuntimeException {

	public CustomerAlreadyRegisteredException(String message) {
		super(message);
	}

}
