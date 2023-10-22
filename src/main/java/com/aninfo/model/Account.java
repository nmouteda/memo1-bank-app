package com.aninfo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    private Double cap;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
        this.cap = 0.0;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setCap(Double cap){this.cap = cap;}
    public Double getCap(){return this.cap;}

    public boolean maxCapReached(Double maxCap) {
        return this.cap.equals(maxCap);

    }
}
