package com.app.service;

import java.util.List;

import com.app.dto.SanctionedLoanDTO;

public interface SanctionedLoanService {

	SanctionedLoanDTO addSactionedLoan(Integer applId, SanctionedLoanDTO sancLoanDto);

	List<SanctionedLoanDTO> getSanctionedLoanOfCustomer(Integer custId);
	
	List<SanctionedLoanDTO> sortByAmountDisbursed(Integer customerId);

	List<SanctionedLoanDTO> getAllSanctionedLoans();
	
	List<SanctionedLoanDTO> getAllSanctionedLoans(int page, int size);

	SanctionedLoanDTO updateSanctionedLoan(Integer applId, SanctionedLoanDTO sancLoanDto);
}
