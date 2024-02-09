package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDTO;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
	@Autowired
	private CustomerService custService;
	
	
	public CustomerController() {
		
	}
	
	@GetMapping("/admin/all")
	public List<CustomerDTO> listAllCustomers(){
		return custService.getAllCustomers();
	}
	
	//Method to get Details of a specific customer
	@GetMapping("/{custId}")
	public CustomerDTO getCustomerDetails(@PathVariable Integer custId){
		return custService.getCustomerDetails(custId);
	}
	
	@PostMapping
	public CustomerDTO addCustomer(@RequestBody CustomerDTO custDto) {
		return custService.addCustomer(custDto);
	}
	
	@PostMapping("/login")
	public CustomerDTO loginCustomer(@RequestParam String email, @RequestParam String password) {
		return custService.loginCustomer(email,password);
	}
	
	
	
}
