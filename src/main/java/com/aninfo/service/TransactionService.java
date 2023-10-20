package com.aninfo.service;

import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Transaction createTransaction(Transaction transaction) {

        Account account = accountRepository.findAccountByCbu(transaction.getCBU());

        if ((transaction.getAmount() < 0) && (account.getBalance() - transaction.getAmount() < 0)) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        if (transaction.getAmount() == 0) {
            throw new DepositNegativeSumException("Cannot deposit anything");
        }

        Double promo = Double.valueOf(0);

        if (transaction.getAmount() >= 2000) {
            promo = (10 * transaction.getAmount() / 100);

            if (promo >= 500) {
                promo = Double.valueOf(500);
            }
        }

        Double newBalance = account.getBalance() + transaction.getAmount() + promo;

        account.setBalance(newBalance);
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions(Long cbu) {
        return transactionRepository.findAll().stream()
                .filter(x -> x.getCBU() == cbu)
                .collect(Collectors.toList());
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void deleteById(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

}
