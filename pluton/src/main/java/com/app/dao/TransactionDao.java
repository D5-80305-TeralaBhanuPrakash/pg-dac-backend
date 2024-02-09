package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Customer;
import com.app.entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction,Integer> {
	
	List<Transaction> findByCustomer(Customer cust);
	
	@Query("SELECT t FROM Transaction t JOIN t.loanAppl la WHERE la.id = :applId")
	List<Transaction> findByApplicationId(Integer applId);
}
