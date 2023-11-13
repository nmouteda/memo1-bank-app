package com.aninfo.service;

import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

}
