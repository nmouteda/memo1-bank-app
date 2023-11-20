package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TransactionType type;
    private Double amount;

    public Transaction(Double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }

    public Transaction(){
        
    }
    
    public Double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

}
