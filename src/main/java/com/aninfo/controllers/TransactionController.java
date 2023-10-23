package com.aninfo.controllers;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.service.AccountService;
import com.aninfo.service.TransactionService;
import java.util.Optional;
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

    @Autowired
    private AccountService accountService;

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transactionOptional = transactionService.findById(id);
        return ResponseEntity.of(transactionOptional);
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(@RequestParam Long cbu) {
        Optional<Account> accountOptional = accountService.findById(cbu);
        if (accountOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(accountOptional.get().getTransactions(), HttpStatus.OK);
    }

    @PutMapping("/withdraw/{cbu}")
    public Account withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
        return transactionService.withdraw(cbu, sum);
    }

    @PutMapping("/deposit/{cbu}")
    public Account deposit(@PathVariable Long cbu, @RequestParam Double sum) {
        return transactionService.deposit(cbu, sum);
    }




}
