package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
