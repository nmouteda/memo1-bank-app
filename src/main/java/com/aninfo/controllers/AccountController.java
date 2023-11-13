package com.aninfo.controllers;

import com.aninfo.Memo1BankApp;
import com.aninfo.model.Account;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{cbu}")
    public ResponseEntity<Account> getAccount(@PathVariable Long cbu) {
        Optional<Account> accountOptional = accountService.findById(cbu);
        return ResponseEntity.of(accountOptional);
    }

    @PutMapping("/accounts/{cbu}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
        Optional<Account> accountOptional = accountService.findById(cbu);

        if (!accountOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        account.setCbu(cbu);
        accountService.save(account);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/accounts/{cbu}")
    public void deleteAccount(@PathVariable Long cbu) {
        accountService.deleteById(cbu);
    }

    @PutMapping("/accounts/{cbu}/withdraw")
    public Account withdraw(@PathVariable Long cbu, @RequestParam Double sum) {
        return accountService.withdraw(cbu, sum);
    }

    @PutMapping("/accounts/{cbu}/deposit")
    public Account deposit(@PathVariable Long cbu, @RequestParam Double sum) {
        return accountService.deposit(cbu, sum);
    }
}
