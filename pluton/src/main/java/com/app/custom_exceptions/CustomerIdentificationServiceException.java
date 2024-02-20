package com.app.custom_exceptions;

public class CustomerIdentificationServiceException extends RuntimeException {
	public CustomerIdentificationServiceException(String message) {
        super(message);
    }
}
