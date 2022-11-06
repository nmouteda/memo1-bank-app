package com.aninfo.service;

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
    public Optional<Account> findById(Long cbu) {return accountRepository.findById(cbu);}
    public void save(Account account) {
        accountRepository.save(account);
    }
    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }
}
