package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SanctionedLoanDTO;
import com.app.service.SanctionedLoanService;

@RestController
@RequestMapping("/admin/sanction")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class SanctionedLoanController {

	@Autowired
	private SanctionedLoanService sancLoanService;
	
	
	@PostMapping("/{applId}")
	public SanctionedLoanDTO addSactionedLoan(@PathVariable Integer applId,@RequestBody SanctionedLoanDTO sancLoanDto) {
		return sancLoanService.addSactionedLoan(applId,sancLoanDto);
	}
}
