package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
}
