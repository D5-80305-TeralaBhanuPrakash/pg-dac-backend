package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDTO;
import com.app.entities.Customer;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
	@Autowired
	private CustomerService custService;
	
	
	public CustomerController() {
		
	}
	
	@GetMapping
	public List<Customer> listAllCustomers(){
		return custService.getAllCustomers();
	}
	
	@PostMapping
	public CustomerDTO addCustomer(@RequestBody CustomerDTO custDto) {
		return custService.addCustomer(custDto);
	}
	
	
	
}
