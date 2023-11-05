package com.aninfo.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionNumber;

    private Double sum;
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {
    }

    public Transaction(Double sum, String transactionType, Account account) {
        this.sum = sum;
        this.transactionType = transactionType;
        this.account = account;
    }

    public String getTransactionType(){
        return this.transactionType;
    }
    public Double getSum(){
        return this.sum;
    }
    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionType(String type) {
        this.transactionType = type;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

