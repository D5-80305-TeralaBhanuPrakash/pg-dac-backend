package com.app.service;

import com.app.dto.SanctionedLoanDTO;

public interface SanctionedLoanService {

	SanctionedLoanDTO addSactionedLoan(Integer applId, SanctionedLoanDTO sancLoanDto);

}
