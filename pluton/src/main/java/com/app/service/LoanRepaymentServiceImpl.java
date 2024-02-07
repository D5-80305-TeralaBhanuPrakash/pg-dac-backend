package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.LoanApplicationDao;
import com.app.dao.LoanRepaymentDao;
import com.app.dto.LoanRepaymentDTO;
import com.app.entities.LoanApplication;
import com.app.entities.LoanRepayment;

@Service
@Transactional
public class LoanRepaymentServiceImpl implements LoanRepaymentService{

	@Autowired
	private LoanRepaymentDao loanRepayDao;
	
	@Autowired
	private LoanApplicationDao loanApplDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public LoanRepaymentDTO getLoanRepayDetailsOfApplication(Integer applId) {
		LoanApplication loanAppl = loanApplDao.findById(applId).orElseThrow();
		LoanRepayment loanRepay = loanRepayDao.findByLoanApplication(loanAppl);
		return mapper.map(loanRepay, LoanRepaymentDTO.class);
	}

}
