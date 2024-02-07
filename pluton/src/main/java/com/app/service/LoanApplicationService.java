package com.app.service;

import com.app.dto.LoanApplicationDTO;

public interface LoanApplicationService {

	LoanApplicationDTO addLoanApplicationToCustomer(Integer custId, LoanApplicationDTO loanApplDto);

}
