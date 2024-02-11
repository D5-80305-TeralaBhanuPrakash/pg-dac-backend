package com.app.custom_exceptions;

public class CustomerControllerException extends RuntimeException {
    public CustomerControllerException(String message) {
        super(message);
    }
}
