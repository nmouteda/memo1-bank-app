package com.aninfo.service;

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

    public Transaction createDepositTransaction(Long cbu, Double amount) {
        Transaction transaction = new Transaction(cbu, amount, Transaction.DEPOSIT);
        return transactionRepository.save(transaction);
    }

    public Transaction createWithdrawTransaction(Long cbu, Double amount) {
        Transaction transaction = new Transaction(cbu, amount, Transaction.WITHDRAW);
        return transactionRepository.save(transaction);
    }

    public  Collection<Transaction> getTransactionsByCbu(Long cbu) {
        return transactionRepository.findAllByCbu(cbu);
    }

    public Optional<Transaction> findById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
