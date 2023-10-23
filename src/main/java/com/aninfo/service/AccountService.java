package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.exceptions.AccountNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (account.getBalance() > 0) {
            account.addTransaction(new Transaction(account.getBalance(), TransactionType.DEPOSIT));
        }
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        Optional<Account> account = accountRepository.findById(cbu);
        if (!account.isPresent()) {
            throw new AccountNotFoundException("Account not found");
        }
        return account;
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        account.addTransaction(new Transaction(sum, TransactionType.WITHDRAW));
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        account.setBalance(account.getBalance() + sum);
        account.addTransaction(new Transaction(sum, TransactionType.DEPOSIT));
        
        if (sum >= 2000 && account.getPromo() > 0){
            Double promoApplied = Math.min(sum * 0.1, account.getPromo());
            account.setBalance(account.getBalance() + promoApplied);
            account.setPromo(account.getPromo() - promoApplied);
            account.addTransaction(new Transaction(promoApplied, TransactionType.PROMO));
        }

        accountRepository.save(account);

        return account;
    }

    public ArrayList<Transaction> getTransactions(Long cbu) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        return account.getTransactions();
    }

    public Transaction getTransaction(Long cbu, int index) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        return account.getTransaction(index);
    }

    @Transactional
    public Account makeTransaction(Long cbu, Transaction t) {
        if (t.getType() == TransactionType.DEPOSIT) {
            return deposit(cbu, t.getAmount());
        } else if (t.getType() == TransactionType.WITHDRAW) {
            return withdraw(cbu, t.getAmount());
        } else {
            throw new InvalidTransactionTypeException("Invalid Transaction");
        }
    }

}
