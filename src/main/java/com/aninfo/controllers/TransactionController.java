package com.aninfo.controllers;

import com.aninfo.model.Transaction;
import com.aninfo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions/{cbu}")
    public Collection<Transaction> getTransactions(@PathVariable Long cbu) {
        return transactionService.findByCBU(cbu);
    }

    @GetMapping("/transactions/{cbu}/{id}")
    public Transaction getTransaction(@PathVariable Long cbu, @PathVariable Long id) {
        return transactionService.findById(id);
    }

    @DeleteMapping("/transactions/{cbu}/{id}")
    public void deleteTransaction(@PathVariable Long cbu, @PathVariable Long id) {
        transactionService.deleteById(id);
    }


}
