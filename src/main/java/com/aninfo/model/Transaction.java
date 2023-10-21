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
    private Long accCbu;
    private Double sum;
    private TransactionType type;


    public Transaction()
    {

    }
    public Transaction(Double sum, TransactionType type, Long cbu) {
        this.sum = sum;
        this.type = type;
        this.accCbu = cbu;
    }

    public Long getId() {
        return this.id;
    }

    public Double getSum()
    {
        return this.sum;
    }

    public TransactionType getType()
    {
        return this.type;
    }

    public Long getAccCbu()
    {
        return this.accCbu;
    }
}

