package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUST_GEN")
    private Long transactionId;
    private Double amount;
    private Long cbu;
    private String type;

    public static final String DEPOSIT = "Deposit";
    public static final String WITHDRAW = "Withdraw";
    public Transaction(){
    }
    public Transaction(Long cbu, Double amount, String type){
        this.cbu = cbu;
        this.amount = amount;
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getCbu() {
        return cbu;
    }

    public String getType() {
        return type;
    }

    public Long getTransactionId() {
        return transactionId;
    }
}
