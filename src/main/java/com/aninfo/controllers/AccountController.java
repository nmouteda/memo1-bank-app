package com.aninfo.controllers;

import com.aninfo.handlers.AccountNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.service.AccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping("/accounts/{cbu}")
    public ResponseEntity<?> getAccount(@PathVariable Long cbu) {
        return new ResponseEntity<>(accountService.findById(cbu), HttpStatus.OK);
    }

    @PutMapping("/accounts/{cbu}")
    public ResponseEntity<?> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
        accountService.findById(cbu);
        account.setCbu(cbu);
        accountService.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{cbu}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long cbu) {
        return new ResponseEntity<>(accountService.deleteById(cbu), HttpStatus.OK);
    }
}
