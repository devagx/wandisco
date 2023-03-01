package com.wandisco.oneui.refactor.exceptions;

/**
 * Custom checked exception for handling errors with customer database deletes
 */
public class DeleteCustomerDataException extends Exception {
    public DeleteCustomerDataException(String errMessage, Throwable throwable) {
        super(errMessage, throwable);
    }
}
