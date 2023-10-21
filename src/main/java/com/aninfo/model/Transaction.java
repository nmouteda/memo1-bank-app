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
    Long accCbu;
    Double sum;
    TransactionType type;


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
}

