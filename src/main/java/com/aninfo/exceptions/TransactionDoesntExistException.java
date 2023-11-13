package com.aninfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TransactionDoesntExistException extends RuntimeException{
    public TransactionDoesntExistException(String message) {
        super(message);
    }
}
