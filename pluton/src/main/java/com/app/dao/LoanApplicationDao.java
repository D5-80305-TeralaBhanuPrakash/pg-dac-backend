package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;
import com.app.entities.LoanApplication;

public interface LoanApplicationDao extends JpaRepository<LoanApplication, Integer> {

	List<LoanApplication> findByCustomer(Customer cust);
	
}
