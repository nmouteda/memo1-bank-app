package com.aninfo.handlers;

public class InvalidTransactionTypeException extends RuntimeException {

    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}
