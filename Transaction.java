package com.example.bank.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionType;

    private double amount;

    private LocalDateTime transactiondate;

    // @JsonIgnore prevents infinite JSON recursion (Account -> Transaction -> Account ...)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {
    }

    public Transaction(int id, String transactionType, double amount,
                       LocalDateTime transactiondate, Account account) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactiondate = transactiondate;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(LocalDateTime transactiondate) {
        this.transactiondate = transactiondate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
