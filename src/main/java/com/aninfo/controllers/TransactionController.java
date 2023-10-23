package com.aninfo.controllers;

import com.aninfo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(@RequestParam Long cbu) {
        return new ResponseEntity<>(transactionService.getAllTransactionsFromAccount(cbu), HttpStatus.OK);
    }

    @PutMapping("/withdraw/{cbu}")
    public ResponseEntity<?> withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
        return new ResponseEntity<>(transactionService.withdraw(cbu, sum), HttpStatus.OK);
    }

    @PutMapping("/deposit/{cbu}")
    public ResponseEntity<?> deposit(@PathVariable Long cbu, @RequestParam Double sum) {
        return new ResponseEntity<>(transactionService.deposit(cbu, sum), HttpStatus.OK);
    }
}
