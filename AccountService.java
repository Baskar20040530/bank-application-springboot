package com.example.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.repo.AccountRepo;
import com.example.bank.repo.TransactionRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo ar;

    @Autowired
    private TransactionRepo tr;

    // Create Account
    public Account addAccount(Account ac) {
        return ar.save(ac);
    }

    // Get All Accounts
    public List<Account> getAllAccounts() {
        return ar.findAll();
    }

    // Get Account By ID
    public Account getAccountById(int id) {

        return ar.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Account not found with ID: " + id)
                );
    }

    // Deposit Money
    public Account deposit(int id, double amount) {

        Account ac = ar.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Account not found with ID: " + id)
                );

        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be greater than 0");
        }

        ac.setBalance(ac.getBalance() + amount);

        Transaction tc = new Transaction();

        tc.setTransactionType("Deposit");
        tc.setAmount(amount);
        tc.setTransactiondate(LocalDateTime.now());
        tc.setAccount(ac);

        tr.save(tc);

        return ar.save(ac);
    }

    // Withdraw Money
    public Account withdraw(int id, double amount) {

        Account ac = ar.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Account not found with ID: " + id)
                );

        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be greater than 0");
        }

        if (ac.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        ac.setBalance(ac.getBalance() - amount);

        Transaction tc = new Transaction();

        tc.setTransactionType("Withdraw");
        tc.setAmount(amount);
        tc.setTransactiondate(LocalDateTime.now());
        tc.setAccount(ac);

        tr.save(tc);

        return ar.save(ac);
    }

    // Delete Account
    public String deleteAccount(int id) {

        if (!ar.existsById(id)) {
            throw new RuntimeException("Account not found with ID: " + id);
        }

        ar.deleteById(id);

        return "Account deleted successfully";
    }
}
