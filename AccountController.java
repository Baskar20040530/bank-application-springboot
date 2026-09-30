package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bank.model.Account;
import com.example.bank.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService as;

    // Create Account
    @PostMapping("/addaccount")
    public Account addAccount(@RequestBody Account ac) {
        return as.addAccount(ac);
    }

    // Get All Accounts
    @GetMapping("/selectall")
    public List<Account> getAllAccounts() {
        return as.getAllAccounts();
    }

    // Get Account By ID
    @GetMapping("/part/{id}")
    public Account getAccountById(@PathVariable int id) {
        return as.getAccountById(id);
    }

    // Deposit
    @PutMapping("/deposit/{id}/{amount}")
    public Account deposit(
            @PathVariable int id,
            @PathVariable double amount) {

        return as.deposit(id, amount);
    }

    // Withdraw
    @PutMapping("/withdraw/{id}/{amount}")
    public Account withdraw(
            @PathVariable int id,
            @PathVariable double amount) {

        return as.withdraw(id, amount);
    }

    // Delete Account
    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id) {
        return as.deleteAccount(id);
    }
}
