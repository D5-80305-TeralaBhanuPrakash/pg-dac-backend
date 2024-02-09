package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	@Query("select c from Customer c where c.email=?1 and c.password=?2")
	Customer findCustomerByEmailAndPassword(String email, String password);
}
