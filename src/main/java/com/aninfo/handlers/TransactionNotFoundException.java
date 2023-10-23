package com.aninfo.handlers;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
