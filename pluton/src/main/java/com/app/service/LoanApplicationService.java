package com.app.service;

import java.util.List;

import com.app.dto.LoanApplicationDTO;

public interface LoanApplicationService {

	LoanApplicationDTO addLoanApplicationToCustomer(Integer custId, LoanApplicationDTO loanApplDto);

	List<LoanApplicationDTO> getLoanApplicationOfEmployee(Integer custId);

}
