package com.aninfo.service;

import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getType() == Transaction.TransactionType.DEPOSIT) {
            accountService.deposit(transaction.getCbu(), transaction.getSum());
        } else if (transaction.getType() == Transaction.TransactionType.WITHDRAW) {
            accountService.withdraw(transaction.getCbu(), transaction.getSum());
        } else {
            throw new InvalidTransactionTypeException("Invalid transaction type");
        }
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Collection<Transaction> findByCbu(Long cbu) {
        return transactionRepository.findByCbu(cbu);
    }

    public Optional<Transaction> findById(Long id) { return transactionRepository.findById(id); }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
