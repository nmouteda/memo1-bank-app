package com.aninfo.integration.cucumber;

import com.aninfo.Memo1BankApp;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.service.AccountService;
import com.aninfo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@ContextConfiguration(classes = Memo1BankApp.class)
@WebAppConfiguration
public class AccountIntegrationServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    Account createAccount(Double balance) {
        return accountService.createAccount(new Account(balance));
    }

    Account withdraw(Account account, Double sum) {
        Transaction transaction = new Transaction(account.getCbu(), "Withdraw", sum);
        transactionService.createTransaction(transaction);

        Optional<Account> updatedAccount = accountService.findById(account.getCbu());
        return updatedAccount.get();
    }

    Account deposit(Account account, Double sum) {
        Transaction transaction = new Transaction(account.getCbu(), "Deposit", sum);
        transactionService.createTransaction(transaction);

        Optional<Account> updatedAccount = accountService.findById(account.getCbu());
        return updatedAccount.get();
    }

}
