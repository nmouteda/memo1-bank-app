package com.aninfo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository repository;
    
    public Transaction createTransaction(Double amount, TransactionType type) {
        Transaction t = new Transaction(amount, type);
        return repository.save(t);
    }

    public List<Transaction> getAllTransactions(){
        return repository.findAll();
    }

    public Optional<Transaction> findTransactionById(Long id){
        return repository.findById(id);
    }
}
