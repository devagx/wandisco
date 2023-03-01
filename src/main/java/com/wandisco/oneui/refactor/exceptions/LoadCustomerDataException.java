package com.wandisco.oneui.refactor.exceptions;

/**
 * Custom checked exception for handling errors with loading customers from the database
 */
public class LoadCustomerDataException extends Exception {
    public LoadCustomerDataException(String errMessage, Throwable throwable) {
        super(errMessage, throwable);
    }
}
