package com.app.service;

import java.util.List;

import com.app.dto.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> getAllCustomers();

	CustomerDTO addCustomer(CustomerDTO cust);
	
	CustomerDTO getCustomerDetails(Integer custId);

	CustomerDTO loginCustomer(String email, String password);

	CustomerDTO editCustomer(Integer custId, CustomerDTO custDto);

	String deleteCustomer(Integer custId);
	
	List<CustomerDTO> getCustomersSortedByRegistrationDateAsc();

	List<CustomerDTO> getCustomersSortedByRegistrationDateDesc();

}
