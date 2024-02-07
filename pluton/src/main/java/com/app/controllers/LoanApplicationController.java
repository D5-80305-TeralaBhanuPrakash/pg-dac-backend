package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoanApplicationDTO;
import com.app.service.LoanApplicationService;

@RestController
@RequestMapping("/customer/loanAppl")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationService loanApplService;
	
	@GetMapping("/{custId}")
	public List<LoanApplicationDTO> getLoanApplicationOfEmployee(@PathVariable Integer custId) {
		return loanApplService.getLoanApplicationOfEmployee(custId);
	}
	
	@PostMapping("/{custId}")
	public LoanApplicationDTO addLoanApplicationToCustomer(@PathVariable Integer custId, @RequestBody LoanApplicationDTO loanApplDto) {
		return loanApplService.addLoanApplicationToCustomer(custId, loanApplDto);
	}
	
}
