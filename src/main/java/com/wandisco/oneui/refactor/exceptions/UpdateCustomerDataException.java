package com.wandisco.oneui.refactor.exceptions;

/**
 * Custom checked exception for handling errors with customer database updates
 */
public class UpdateCustomerDataException extends Exception {
    public UpdateCustomerDataException(String errMessage, Throwable throwable) {
        super(errMessage, throwable);
    }
}
