package com.app.service;

import java.util.List;

import com.app.dto.LoanApplicationDTO;

public interface LoanApplicationService {

	LoanApplicationDTO addLoanApplicationToCustomer(Integer custId, LoanApplicationDTO loanApplDto);

	List<LoanApplicationDTO> getLoanApplicationOfEmployee(Integer custId);

	List<LoanApplicationDTO> getAllLoanApplications();

	LoanApplicationDTO setRejectionStatus(String rejectReason, Integer applId);

	LoanApplicationDTO getApplicationByApplicationId(Integer applId);

}
