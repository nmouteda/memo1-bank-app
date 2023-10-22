package com.aninfo.controller;

import com.aninfo.model.Account;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @PutMapping("{cbu}")
    public void deposit(@PathVariable Long cbu, @RequestBody Double sum){
        this.accountService.deposit(cbu,sum);
    }
}
