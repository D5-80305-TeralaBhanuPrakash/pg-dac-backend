package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;
import com.app.entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction,Integer> {
	
	List<Transaction> findByCustomer(Customer cust);
}
