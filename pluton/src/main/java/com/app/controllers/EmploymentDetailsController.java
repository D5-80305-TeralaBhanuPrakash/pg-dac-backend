package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmploymentDetailsDTO;
import com.app.service.EmploymentDetailsService;

@RestController
@RequestMapping("/customer/employer")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class EmploymentDetailsController {
	@Autowired
	private EmploymentDetailsService empDetailsService;
	
	@PostMapping("/{custId}")
	@PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
	public EmploymentDetailsDTO addEmployerToCustomer(@PathVariable Integer custId, @RequestBody EmploymentDetailsDTO empDetailsDto) {
		return empDetailsService.addEmployerToCustomer(custId,empDetailsDto);
	}
	
}
