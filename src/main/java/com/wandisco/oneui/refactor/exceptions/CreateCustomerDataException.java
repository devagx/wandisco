package com.wandisco.oneui.refactor.exceptions;

/**
 * Custom checked exception for handling errors with customer database creations
 */
public class CreateCustomerDataException extends Exception {
    public CreateCustomerDataException(String errMessage, Throwable throwable) {
        super(errMessage, throwable);
    }
}
