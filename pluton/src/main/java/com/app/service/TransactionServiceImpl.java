package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CustomerDao;
import com.app.dao.LoanApplicationDao;
import com.app.dao.TransactionDao;
import com.app.dto.TransactionDTO;
import com.app.entities.Customer;
import com.app.entities.LoanApplication;
import com.app.entities.Transaction;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private LoanRepaymentService loanRepayService;
	
	@Autowired
	private TransactionDao transacDao;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private LoanApplicationDao loanApplDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public TransactionDTO addTrasactionsOfCustomer(Integer custId, TransactionDTO trasacDto) {
		Customer cust = custDao.findById(custId).orElseThrow();
		LoanApplication loanAppl = loanApplDao.findById(trasacDto.getApplicationId()).orElseThrow();
		Transaction transac = mapper.map(trasacDto,Transaction.class);
		transac.setLoanAppl(loanAppl);
		transac.setCustomer(cust);
		loanRepayService.updateLoanRepayment(transac);
		transacDao.save(transac);
		return mapper.map(transac, TransactionDTO.class);
	}

	@Override
	public List<TransactionDTO> getAllTransactions() {
		
		return transacDao.findAll().stream().map(transac -> mapper.map(transac, TransactionDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TransactionDTO> getAllTransactionOfCustomer(Integer custId) {
		Customer cust = custDao.findById(custId).orElseThrow();
		
		return transacDao.findByCustomer(cust).stream()
				.map(transac -> mapper.map(transac, TransactionDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<TransactionDTO> getAllTransactionOfLoan(Integer applId) {
		// TODO Auto-generated method stub
		return transacDao.findByApplicationId(applId).stream()
				.map(transac -> mapper.map(transac, TransactionDTO.class))
				.collect(Collectors.toList());
	}

}
