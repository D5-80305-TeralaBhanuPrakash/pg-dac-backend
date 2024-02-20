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

import com.app.dto.EmploymentDetailsDTO;
import com.app.service.EmploymentDetailsService;

@RestController
@RequestMapping("/user/employer")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class EmploymentDetailsController {
	@Autowired
	private EmploymentDetailsService empDetailsService;
	
	@PostMapping("/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public EmploymentDetailsDTO addEmployerToCustomer(@PathVariable Integer custId, @RequestBody EmploymentDetailsDTO empDetailsDto) {
		return empDetailsService.addEmployerToCustomer(custId,empDetailsDto);
	}
	
	@GetMapping("/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public EmploymentDetailsDTO getEmployerOfCustomer(@PathVariable Integer custId) {
		return empDetailsService.getEmployerOfCustomer(custId);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public List<EmploymentDetailsDTO> getEmployers(){
		return empDetailsService.getAllEmployers();
	}
}
