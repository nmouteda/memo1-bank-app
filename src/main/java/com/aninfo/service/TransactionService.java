package com.aninfo.service;

import com.aninfo.enums.TransactionType;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> findById(Long cbu) {
        return transactionRepository.findById(cbu);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(sum);
        transaction.setAccount(account);
        transaction.setType(TransactionType.WITHDRAW);

        account.setBalance(account.getBalance() - sum);
        account.addTransaction(transaction);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {
        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);

        Transaction transaction = new Transaction();
        transaction.setAmount(sum);
        transaction.setAccount(account);
        transaction.setType(TransactionType.DEPOSIT);

        account.setBalance(account.getBalance() + sum);
        account.addTransaction(transaction);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return account;
    }

}
