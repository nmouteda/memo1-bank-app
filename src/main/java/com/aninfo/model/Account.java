package com.aninfo.model;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    private ArrayList<Long> transactions;

    public Account(){
        this.transactions = new ArrayList<Long>();
    }

    public Account(Double balance) {
        this.balance = Double.valueOf(0);
        this.transactions = new ArrayList<Long>();
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }


   private Transaction createTransaction(Double sum,TransactionType type) {
        Transaction transaction = new Transaction(sum,type,this.cbu);
        return transaction;
    }
    public void addTransaction(Transaction transaction)
    {
        this.transactions.add(transaction.getId());
    }

    public Transaction withdraw(Double sum)
    {
        this.balance -= sum;
        return createTransaction(sum,TransactionType.Extraction);
    }

    public Transaction deposit(Double sum)
    {
        this.balance += sum;
        return createTransaction(sum,TransactionType.Deposit);
    }

    public ArrayList<Long> getTransactions() {
        return this.transactions;
    }

    private void restoreBalance(Double sum,TransactionType type)
    {
        if (type.equals(TransactionType.Extraction) )
        {
            this.balance += sum;

        }
        else
        {
            this.balance -= sum;
        }

    }

    public Account deleteTransaction(Transaction transaction) {
        this.transactions.remove(transaction.getId());
        restoreBalance(transaction.getSum(),transaction.getType());
        return this;
    }
}
