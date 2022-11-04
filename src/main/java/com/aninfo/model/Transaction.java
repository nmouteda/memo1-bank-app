package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionID;

    private String type;

    private Long cbu;

    private Double amount;

    public Transaction() {
    }

    public Transaction(double amount) { this.amount = amount; }

    public Long getTransactionID() {return transactionID; }

    public void setTransactionID(Long transactionID) { this.transactionID = transactionID; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Long getCbu() { return cbu; }

    public void setCbu(Long cbu) { this.cbu = cbu; }

    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }
}
