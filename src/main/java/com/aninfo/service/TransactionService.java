package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction)
    {
        return transactionRepository.save(transaction);
    }
    public void save(Transaction transaction)
    {
        transactionRepository.save(transaction);
    }


    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }


    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
