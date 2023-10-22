package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.NonExistentTransactionException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
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

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        Transaction transaction = account.withdraw(sum);
        transaction = transactionService.createTransaction(transaction);
        account.addTransaction(transaction);
        save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        Transaction transaction = account.deposit(sum);
        transaction = transactionService.createTransaction(transaction);
        account.addTransaction(transaction);
        save(account);

        return account;
    }

    public Collection<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    public Collection<Transaction> getTransactionsByAccount(Long cbu) {
        Account account = accountRepository.findAccountByCbu(cbu);
        return transactionService.getTransactionsByCbu(account.getTransactions());
    }

    public void deleteTransaction(Long id) {
        Optional<Transaction> transaction = transactionService.findById(id);
        if (transaction.isEmpty())
        {
            throw new NonExistentTransactionException("Transaction not found");
        }
        Account account = accountRepository.findAccountByCbu(transaction.get().getAccCbu());
        account = account.deleteTransaction(transaction.get());
        transactionService.deleteById(id);
        save(account);
    }

    public Optional<Transaction> getTransaction(Long id) {
        return transactionService.findById(id);
    }

    public Account depositWithPromo(Long cbu, Double sum) {
        if( sum >= 2000)
        {
            if (sum*0.1 <= 500)
            {
                sum += sum*0.1;
            }
            else
            {
                sum +=500;
            }
        }
        return deposit(cbu,sum);
    }
}
