package com.aninfo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.model.Account;
import com.aninfo.service.AccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
	public Collection<Account> getAccounts() {
		return accountService.getAccounts();
	}
    
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestParam double initialBalance) {
        if (initialBalance < 0) {
            throw new DepositNegativeSumException("Balance debe ser mayor que 0");
        }
		Account account = new Account(initialBalance);
		return accountService.createAccount(account);
	}

    @GetMapping("/{cbu}")
	public Account getAccount(@PathVariable Long cbu) {
		Account account = accountService.findById(cbu);
		return account;
	}

    @PutMapping("/{cbu}")
	public Account updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
		Account updateAccount = accountService.findById(cbu);
		updateAccount.setCbu(cbu);
        updateAccount.setBalance(account.getBalance());
        updateAccount.setPromo(account.getPromo());
		accountService.save(updateAccount);
		return updateAccount;
	}

    @DeleteMapping("/{cbu}")
	public void deleteAccount(@PathVariable Long cbu) {
		accountService.deleteById(cbu);
	}

}
