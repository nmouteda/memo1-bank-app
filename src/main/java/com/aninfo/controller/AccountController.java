package com.aninfo.controller;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping
    public void createAccount(@RequestBody Account account){
        this.accountService.createAccount(account);
    }

    @GetMapping("/list")
    public Collection<Account> getAccounts(){
        return this.accountService.getAccounts();
    }

    @GetMapping("{cbu}")
    public Optional<Account> findById(@PathVariable Long cbu){
        return this.accountService.findById(cbu);
    }

    @PutMapping("/save")
    public void save(@RequestBody Account account){
        this.accountService.save(account);
    }

    @DeleteMapping("{cbu}")
    public void deleteById(@PathVariable Long cbu){
        this.accountService.deleteById(cbu);
    }

    @PatchMapping("/deposit/{cbu}")
    public void deposit(@PathVariable Long cbu, @RequestBody Double sum){
        this.accountService.deposit(cbu,sum);
    }

    @PatchMapping("/withdraw/{cbu}")
    public void withdraw(@PathVariable Long cbu, @RequestBody Double sum){
        this.accountService.withdraw(cbu,sum);
    }

    @GetMapping("/list/transactions/{cbu}/{transactionNumber}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long cbu){
        List<Transaction> list = accountService.getTransactions(cbu);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/transaction/by/{cbu}/{transactionNumber}")
    public ResponseEntity<Transaction> getTransactions(@PathVariable Long cbu,@PathVariable Long transactionNumber){
        Transaction transaction = accountService.getTransaction(cbu,transactionNumber);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/transaction/delete/by/{cbu}/{transactionNumber}")
    public void delete_transaction(@PathVariable Long cbu, @PathVariable Long transactionNumber){
        this.accountService.deleteTransaction(cbu,transactionNumber);
    }

}
