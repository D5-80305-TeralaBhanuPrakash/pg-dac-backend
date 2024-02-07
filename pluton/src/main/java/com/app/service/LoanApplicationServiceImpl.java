package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CustomerDao;
import com.app.dao.EmploymentDetailsDao;
import com.app.dao.LoanApplicationDao;
import com.app.dto.LoanApplicationDTO;
import com.app.entities.Customer;
import com.app.entities.EmploymentDetails;
import com.app.entities.LoanApplication;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService{
	
	@Autowired
	private LoanApplicationDao loanApplDao;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private EmploymentDetailsDao empDetailsDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public LoanApplicationDTO addLoanApplicationToCustomer(Integer custId, LoanApplicationDTO loanApplDto) {
		Customer cust = custDao.findById(custId).orElseThrow();
		EmploymentDetails empDetails =  empDetailsDao.findById(custId).orElseThrow();
		//System.out.println(cust);
		//System.out.println(empDetails);
		//System.out.println(loanApplDto);
		LoanApplication loanAppl = mapper.map(loanApplDto, LoanApplication.class);
		loanAppl.setCustomer(cust);
		loanAppl.setEmploymentDetails(empDetails);
		loanApplDao.save(loanAppl);
		return mapper.map(loanAppl, LoanApplicationDTO.class);
	}

	@Override
	public List<LoanApplicationDTO> getLoanApplicationOfEmployee(Integer custId) {
		Customer cust = custDao.findById(custId).orElseThrow();
		List<LoanApplication> loanApplList = loanApplDao.findByCustomer(cust);
		
		
		return loanApplList
				.stream() //Stream<Dept>
				.map(loanAppl -> mapper.map(loanAppl,LoanApplicationDTO.class)) //Stream <DTO>
				.collect(Collectors.toList());//List<DTO>;
	}
	
	

}
