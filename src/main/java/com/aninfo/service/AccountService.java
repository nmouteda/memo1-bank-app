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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    /*
    private void addTransaction(Account account, Double amount, TransactionType type){
        Transaction t = transactionService.createTransaction(amount, type);
        account.addTransaction(t);
    }*/

    private void applyPromo(Account account, Double depositAmount){
        if (depositAmount < 2000 || account.getPromo() <= 0){
            return;
        }
        Double promoApplied = Math.min(depositAmount * 0.1, account.getPromo());
        account.setBalance(account.getBalance() + promoApplied);
        account.setPromo(account.getPromo() - promoApplied);

        Transaction promo = transactionService.createTransaction(promoApplied, TransactionType.PROMO);
        account.addTransaction(promo);
    }

    public Account createAccount(Account account) {
        if (account.getBalance() > 0) {
            Transaction transaction = transactionService.createTransaction(account.getBalance(), TransactionType.DEPOSIT);
            account.addTransaction(transaction);
            applyPromo(account, account.getBalance());
        }
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account findById(Long cbu) {
        Optional<Account> account = accountRepository.findById(cbu);
        return account.orElseThrow(() -> new AccountNotFoundException("Account not found"));
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
        Transaction transaction = transactionService.createTransaction(sum, TransactionType.WITHDRAW);
        account.addTransaction(transaction);
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
        Transaction transaction = transactionService.createTransaction(sum, TransactionType.DEPOSIT);
        account.addTransaction(transaction);
        
        applyPromo(account, sum);

        accountRepository.save(account);

        return account;
    }

    public List<Transaction> getTransactions(Long cbu) {
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
        switch (t.getType()) {
            case DEPOSIT:
                return deposit(cbu, t.getAmount());
            case WITHDRAW:
                return withdraw(cbu, t.getAmount());
            default:
                throw new InvalidTransactionTypeException("Invalid Transaction");
        }
    }

    @Transactional
    public void deleteTransaction(Long cbu, int transaction_id) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        // No se si hay que reembolzar la plata o no
        account.deleteTransaction(transaction_id);
    } 

}
