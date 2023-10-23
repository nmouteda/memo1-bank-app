package com.aninfo.service;

import com.aninfo.dtos.MessageDTO;
import com.aninfo.enums.TransactionType;
import com.aninfo.handlers.AccountNotFoundException;
import com.aninfo.handlers.DepositNegativeSumException;
import com.aninfo.handlers.InsufficientFundsException;
import com.aninfo.handlers.TransactionNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.min;

@Service
public class TransactionService {
    private static final Double PROMO_DEPOSIT_MIN = 2000D;
    private static final Double MAX_DEPOSIT_EXTRA = 500D;
    private static final Integer PROMO_BENEFIT = 10;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction findById(Long cbu) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(cbu);
        if (transactionOptional.isEmpty()) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        return transactionOptional.get();
    }

    public MessageDTO deleteById(Long id) {
        try {
            transactionRepository.deleteById(id);
            return new MessageDTO("Delete success");
        } catch (Exception e) {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    public List<Transaction> getAllTransactionsFromAccount(Long cbu) {
        Optional<Account> accountOptional = accountRepository.findById(cbu);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }
        return accountOptional.get().getTransactions();
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
        Double newSum = applyPromo(sum);

        Transaction transaction = new Transaction();
        transaction.setAmount(newSum);
        transaction.setAccount(account);
        transaction.setType(TransactionType.DEPOSIT);

        account.setBalance(account.getBalance() + newSum);
        account.addTransaction(transaction);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return account;
    }

    private Double applyPromo(Double amount) {
        if (amount < PROMO_DEPOSIT_MIN) {
            return amount;
        }
        return amount + min(MAX_DEPOSIT_EXTRA, (amount / PROMO_BENEFIT));
    }

}
