package com.app.custom_exceptions;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message){
		super(message);
	}
}
