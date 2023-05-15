package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
    public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cbu;

    private Double sum;

    private TransactionType type;

    public Transaction(){
    }

    public Transaction(Long cbu, Double sum, TransactionType type) {
        this.cbu = cbu;
        this.sum = sum;
        this.type = type;
    }

    public Long getId() { return id; }

    public Long getCbu() {
        return cbu;
    }

    public Double getSum() { return sum; }

    public TransactionType getType() {
        return type;
    }
}
