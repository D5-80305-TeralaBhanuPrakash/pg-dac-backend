package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/loanAppl")
@CrossOrigin(origins="*")
@Validated
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationService loanApplService;
	
	@GetMapping("/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<List<LoanApplicationDTO>> getLoanApplicationOfEmployee(@PathVariable Integer custId) {
        List<LoanApplicationDTO> loanApplications = loanApplService.getLoanApplicationOfEmployee(custId);
        return ResponseEntity.ok(loanApplications);
    }

    @PostMapping("/{custId}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<LoanApplicationDTO> addLoanApplicationToCustomer(@PathVariable Integer custId, @RequestBody LoanApplicationDTO loanApplDto) {
        LoanApplicationDTO addedLoanApplication = loanApplService.addLoanApplicationToCustomer(custId, loanApplDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedLoanApplication);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LoanApplicationDTO>> getAllLoanApplications() {
        List<LoanApplicationDTO> loanApplications = loanApplService.getAllLoanApplications();
        return ResponseEntity.ok(loanApplications);
    }

    @PostMapping("/admin/reject/{applId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoanApplicationDTO> rejectLoanApplication(@RequestBody String rejectReason, @PathVariable Integer applId) {
        LoanApplicationDTO rejectedLoanApplication = loanApplService.setRejectionStatus(rejectReason, applId);
        return ResponseEntity.ok(rejectedLoanApplication);
    }
    
    @GetMapping("get/{applId}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<LoanApplicationDTO> getApplicationByApplicationId(@PathVariable Integer applId){
    	LoanApplicationDTO applDto = loanApplService.getApplicationByApplicationId(applId);
    	return ResponseEntity.ok(applDto);
    }
}
