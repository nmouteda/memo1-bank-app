package com.aninfo.service;

import com.aninfo.enums.TransactionType;
import com.aninfo.exceptions.TransactionDoesntExistException;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private static final double MIN_PROMO = 2000;
    private static final double MAX_GIFT = 500;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction findById(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    public List<Transaction> findByCBU(Long cbu) {
        return transactionRepository.findByAccountCbu(cbu);
    }

    public double createTransaction(Transaction transaction) {
        double sum = transaction.getAmount();

        if ((transaction.getType() == TransactionType.DEPOSIT) && (sum >= MIN_PROMO)) {
            double gift = 0.1 * sum;

            if (gift <= MAX_GIFT)
                transaction.setAmount(sum + gift);
            else
                transaction.setAmount(sum + MAX_GIFT);
        }

        transactionRepository.save(transaction);
        return transaction.getAmount();
    }

    public void deleteById(Long id) {
        try {
            transactionRepository.deleteById(id);
        } catch (Exception e) {
            throw new TransactionDoesntExistException("Transaction doesn't exist");
        }
    }

}

