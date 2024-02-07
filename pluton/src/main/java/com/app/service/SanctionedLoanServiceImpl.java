package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.LoanApplicationDao;
import com.app.dao.SanctionedLoanDao;
import com.app.dto.SanctionedLoanDTO;
import com.app.entities.LoanApplication;
import com.app.entities.SanctionedLoan;

@Service
@Transactional
public class SanctionedLoanServiceImpl implements SanctionedLoanService{

	@Autowired
	private SanctionedLoanDao sancDao;
	
	@Autowired
	private LoanApplicationDao applDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SanctionedLoanDTO addSactionedLoan(Integer applId, SanctionedLoanDTO sancLoanDto) {
		LoanApplication loanAppl = applDao.findById(applId).orElseThrow();
		SanctionedLoan sancLoan = mapper.map(sancLoanDto, SanctionedLoan.class);
		sancLoan.setLoanApplication(loanAppl);
		sancDao.save(sancLoan);
		return mapper.map(sancLoan, SanctionedLoanDTO.class);
	}
	
	
	
}
