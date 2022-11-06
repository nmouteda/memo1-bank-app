package com.aninfo.service;

import com.aninfo.exceptions.*;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        String type = transaction.getType();
        if (!type.equals("Withdraw")) {
            if (!type.equals("Deposit")) {
                throw new InvalidTransactionTypeException(String.format("Transaction type '%s' not accepted", transaction.getType()));
            }
        }

        Optional<Account> account = accountService.findById(transaction.getCbu());
        if (account.isEmpty()){
            throw new AccountNotFoundException(String.format("Account with cbu '%s' not found",transaction.getCbu()));
        }

        if (type.equals("Withdraw")) {
            AccountExtraction(account.get(), transaction);
        } else {
            AccountAddFunds(account.get(), transaction);
        }

        return transactionRepository.save(transaction);
    }

    private void AccountExtraction(Account account, Transaction transaction) {
        if (account.getBalance() >= transaction.getAmount()) {
            transaction.setPastBalance(account.getBalance());
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }

    private void AccountAddFunds(Account account, Transaction transaction) {
        if (transaction.getAmount() < 0){
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        transaction.setPastBalance(account.getBalance());
        account.setBalance(account.getBalance() + ApplyPromo(transaction.getAmount()));
    }

    private Double ApplyPromo(Double amount) {
        if (amount >= 5000){
            return amount + 500;
        }

        if (amount < 2000) {
            return amount;
        }

        return amount + ((amount * 10) / 100);
    }

    public Collection<Transaction> getTransactions(Long cbu) {
        return transactionRepository.findTransactionByCbu(cbu);
    }

    public Transaction getTransaction(Long id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        if (transaction == null){
            throw new TransactionNotFoundException(String.format("Transaction with id %s not found",id));
        }

        return transactionRepository.findTransactionById(id);
    }

    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException(String.format("Transaction with id %s not found",id));
        }
        transactionRepository.deleteById(id);
    }
}
