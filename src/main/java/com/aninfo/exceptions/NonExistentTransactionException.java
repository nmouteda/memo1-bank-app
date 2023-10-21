package com.aninfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NonExistentTransactionException extends RuntimeException {

    public NonExistentTransactionException(String message) {
        super(message);
    }
}
