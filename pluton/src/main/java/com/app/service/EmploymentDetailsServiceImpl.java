package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CustomerDao;
import com.app.dao.EmploymentDetailsDao;
import com.app.dto.EmploymentDetailsDTO;
import com.app.entities.Customer;
import com.app.entities.EmploymentDetails;

@Service
@Transactional
public class EmploymentDetailsServiceImpl implements EmploymentDetailsService{
	
	@Autowired
	private EmploymentDetailsDao empDetailsDao;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public EmploymentDetailsDTO addEmployerToCustomer(Integer custId, EmploymentDetailsDTO empDetailsDto) {
		Customer cust = custDao.findById(custId).orElseThrow();
		
		System.out.println(cust);
		
		EmploymentDetails empDetails = mapper.map(empDetailsDto, EmploymentDetails.class);
		
		empDetails.setCustomer(cust);
		empDetailsDao.save(empDetails);
		return mapper.map(empDetails, EmploymentDetailsDTO.class);
	}

}
