package com.example.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
