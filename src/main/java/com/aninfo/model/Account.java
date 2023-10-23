package com.aninfo.model;

import org.springframework.util.MultiValueMap;

import javax.persistence.*;
import java.util.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    private Double cap;
    @ElementCollection
    @CollectionTable(name = "account_transactions", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "transaction")
    private List<Transaction> transactions;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
        this.cap = 0.0;
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

    public void setCap(Double cap){this.cap = cap;}
    public Double getCap(){return this.cap;}

    public boolean maxCapReached(Double maxCap) {
        return this.cap.equals(maxCap);
    }
    /*
    public void registerTransaction(String type, Double sum){
        this.transactions.add(new Transaction(type,sum));
    }*/
    public List<Transaction> getTransactions(){
        return this.transactions;
    }
}
