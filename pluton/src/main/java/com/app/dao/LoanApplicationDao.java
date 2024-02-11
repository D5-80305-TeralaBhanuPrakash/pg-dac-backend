package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;
import com.app.entities.LoanApplication;

public interface LoanApplicationDao extends JpaRepository<LoanApplication, Integer> {

	Optional<List<LoanApplication>> findByCustomer(Customer cust);
	
}
