package com.app.custom_exceptions;

public class AddressNotFoundException extends RuntimeException {

	public AddressNotFoundException(String msg) {
		super(msg);
	}
}
