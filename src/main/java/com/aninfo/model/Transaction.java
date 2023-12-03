package com.aninfo.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private final TransactionType type;
    private final Double amount;

    public Transaction(Double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

}
