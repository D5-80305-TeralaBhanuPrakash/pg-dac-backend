package com.app.custom_exceptions;

public class LoanApplicationServiceException extends RuntimeException {
    public LoanApplicationServiceException(String message) {
        super(message);
    }
}
