package com.aninfo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;
    @OneToMany
    private Collection<Transaction> transactions;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
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

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) { this.transactions.add(transaction); }

    public Collection<Transaction> getTransactions() { return transactions; }
}
