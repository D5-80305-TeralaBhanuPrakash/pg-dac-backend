package com.app.controllers;

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

import com.app.dto.LoanRepaymentDTO;
import com.app.service.LoanRepaymentService;

@RestController
@RequestMapping("/admin/repayment")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class LoanRepaymentController {
	@Autowired
	private LoanRepaymentService loanRepayService;
	
	@GetMapping("/{applId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")	
	public LoanRepaymentDTO getLoanRepayDetailsOfApplication(@PathVariable Integer applId) {
		return loanRepayService.getLoanRepayDetailsOfApplication(applId);
	}
	
	@PostMapping("/{applId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public LoanRepaymentDTO addLoanRepaymentDetails(@PathVariable Integer applId, @RequestBody LoanRepaymentDTO loanRepDto) {
		return loanRepayService.addLoanRepaymentDetails(applId,loanRepDto);
	}
}
