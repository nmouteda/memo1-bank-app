package com.aninfo.service;

import com.aninfo.dtos.MessageDTO;
import com.aninfo.handlers.AccountNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account findById(Long cbu) {
        Optional<Account> accountOptional = accountRepository.findById(cbu);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }
        return accountOptional.get();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public MessageDTO deleteById(Long cbu) {
        try {
            accountRepository.deleteById(cbu);
            return new MessageDTO("Delete success");
        } catch (Exception e) {
            throw new AccountNotFoundException("Account not found");
        }
    }
}
