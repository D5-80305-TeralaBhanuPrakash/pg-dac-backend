package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerIdentificationDTO;
import com.app.service.CustomerIdentificationService;

@RestController
@RequestMapping("/customer/identification")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class CustomerIdentificationController {
	@Autowired
	private CustomerIdentificationService custIdenService;
	
	@PostMapping("/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public CustomerIdentificationDTO addIdentificationToCustomer(@PathVariable Integer custId, @RequestBody CustomerIdentificationDTO custIdentDto) {
		return custIdenService.addIdentificationToCustomer(custId,custIdentDto);
	}
	
	@GetMapping("/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public CustomerIdentificationDTO getCustomerIdentification(@PathVariable Integer custId) {
		return custIdenService.getCustomerIdentification(custId);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<CustomerIdentificationDTO> getAllCustomerIdentification() {
		return custIdenService.getAllCustomerIdentification();
	}
	
	
}
