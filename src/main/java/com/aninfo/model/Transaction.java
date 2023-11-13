package com.aninfo.model;

import com.aninfo.enums.TransactionType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private TransactionType type;

    private double amount;

    private long accountCbu;


    public Transaction(){}

    public Transaction(TransactionType type, double amount, long accountCbu) {
        this.type = type;
        this.amount = amount;
        this.accountCbu = accountCbu;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public long getAccountCbu() {
        return accountCbu;
    }

    public long getId(){
        return id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
