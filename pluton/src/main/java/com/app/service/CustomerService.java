package com.app.service;

import java.util.List;

import com.app.dto.CustomerDTO;
import com.app.entities.Customer;

public interface CustomerService {

	List<CustomerDTO> getAllCustomers();

	CustomerDTO addCustomer(CustomerDTO cust);
	

}
