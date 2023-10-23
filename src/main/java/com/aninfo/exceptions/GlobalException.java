package com.aninfo.exceptions;

import com.aninfo.dtos.MessageDTO;
import com.aninfo.handlers.AccountNotFoundException;
import com.aninfo.handlers.DepositNegativeSumException;
import com.aninfo.handlers.InsufficientFundsException;
import com.aninfo.handlers.TransactionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(DepositNegativeSumException.class)
    public ResponseEntity<MessageDTO> depositNegative(RuntimeException e){
        return ResponseEntity.badRequest().body(
            new MessageDTO(e.getMessage())
        );
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<MessageDTO> insufficientFunds(RuntimeException e){
        return ResponseEntity.badRequest().body(
            new MessageDTO(e.getMessage())
        );
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<MessageDTO> accountNotFound(RuntimeException e){
        return ResponseEntity.badRequest().body(
            new MessageDTO(e.getMessage())
        );
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<MessageDTO> transactionNotFound(RuntimeException e){
        return ResponseEntity.badRequest().body(
            new MessageDTO(e.getMessage())
        );
    }
}
