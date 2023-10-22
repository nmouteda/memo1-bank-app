package com.aninfo.model;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance; //Es un error grave usar doubles para balances

    @Lob
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>(); 
    // Si tuviera mas tiempo lo haria en otra tabla

    private Double promo = 500.0;


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

    public void addTransaction(Transaction t) {
        this.transactions.add(t);
    }

    public void deleteTransaction(int index) {
        transactions.remove(index);
    }

    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

}
