package com.app.service;

import com.app.dto.LoanRepaymentDTO;
import com.app.entities.SanctionedLoan;
import com.app.entities.Transaction;

public interface LoanRepaymentService {

	LoanRepaymentDTO getLoanRepayDetailsOfApplication(Integer applId);

	LoanRepaymentDTO initializeLoanRepayment(SanctionedLoan loanAppl);
	
	LoanRepaymentDTO updateLoanRepayment(Transaction transaction);

	LoanRepaymentDTO addLoanRepaymentDetails(Integer applId, LoanRepaymentDTO loanRepDto);
}
