package com.aninfo.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Transaction {
    @Column(name = "transaction_sum")
    private Double sum;
    @Column(name = "transaction_type")
    private String transactionType;

    public Transaction(){

    }
    @PersistenceConstructor
    public Transaction(Double sum, String transactionType) {
        this.sum = sum;
        this.transactionType = transactionType;
    }
    public String getTransactionType(){return this.transactionType;}
    public Double getSum(){return this.sum;}
}
