package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cbu;

    private Double amount;

    public Transaction() {

    }

    public Transaction(Double amount, Long cbu, Long id) {
        this.amount = amount;
        this.cbu = cbu;
        this.id = id;
    }

    public Double getAmount() { return this.amount; }

    public Long getCBU() { return this.cbu; }

    public Long getId() {
        return this.id;
    }
}
