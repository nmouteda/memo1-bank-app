package com.aninfo.controllers;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aninfo.service.AccountService;
import com.aninfo.service.TransactionService;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @GetMapping
    public Collection<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{cbu}")
	public List<Transaction> getTransactions(@PathVariable Long cbu) {
		return accountService.getTransactions(cbu);
	}

	@GetMapping("/{cbu}/{transactionIndex}")
	public Transaction getTransaction(@PathVariable Long cbu, @PathVariable int transactionIndex) {
		return accountService.getTransaction(cbu, transactionIndex);
	}

	@PostMapping("/{cbu}")
	public Account makeTransaction(@PathVariable Long cbu, @RequestBody Transaction transaction) {
        if (transaction.getAmount() < 0) {
            throw new Error("Amount debe ser mayor que 0");
        }
		return accountService.makeTransaction(cbu, transaction);
	}

	@DeleteMapping("/{cbu}/{transaction_id}")
	public void deleteTransaction(@PathVariable Long cbu, @PathVariable int transaction_id) {
		accountService.deleteTransaction(cbu, transaction_id);
	}
}
