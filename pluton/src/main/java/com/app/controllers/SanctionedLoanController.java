package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SanctionedLoanDTO;
import com.app.service.SanctionedLoanService;

@RestController
@RequestMapping("/sanction")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class SanctionedLoanController {

	@Autowired
	private SanctionedLoanService sancLoanService;
	
	
	@PostMapping("/{applId}")
	public SanctionedLoanDTO addSactionedLoan(@PathVariable Integer applId,@RequestBody SanctionedLoanDTO sancLoanDto) {
		return sancLoanService.addSactionedLoan(applId,sancLoanDto);
	}
	
	@GetMapping("/{custId}")
	public List<SanctionedLoanDTO> getSanctionedLoanOfCustomer(@PathVariable Integer custId) {
		return sancLoanService.getSanctionedLoanOfCustomer(custId);
	}
	
	@GetMapping("/sortByAmount/{custId}")
	public List<SanctionedLoanDTO> sortByAmountDisbursed(@PathVariable Integer custId){
		return sancLoanService.sortByAmountDisbursed(custId);
	}
	
	@GetMapping("/admin")
	public List<SanctionedLoanDTO> getAllSanctionedLoans(){
		return sancLoanService.getAllSanctionedLoans();
	}
	
	//url -> http://localhost:8080/admin/all?page=0&size=10
	@GetMapping("/admin/all")
    public ResponseEntity<List<SanctionedLoanDTO>> getAllSanctionedLoans(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        
        List<SanctionedLoanDTO> sanctionedLoanDTOs = sancLoanService.getAllSanctionedLoans(page, size);
        return ResponseEntity.ok(sanctionedLoanDTOs);
    }
	
	
	
	
	
	
	
	
	
	
	
}
