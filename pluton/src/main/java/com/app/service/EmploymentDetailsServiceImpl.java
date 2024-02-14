package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.CustomerNotFoundException;
import com.app.dao.CustomerDao;
import com.app.dao.EmploymentDetailsDao;
import com.app.dto.CustomerIdentificationDTO;
import com.app.dto.EmploymentDetailsDTO;
import com.app.entities.Customer;
import com.app.entities.CustomerIdentification;
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
		Customer cust = custDao.findById(custId).orElseThrow(()->new CustomerNotFoundException("customer not found"));
		
		System.out.println(cust);
		
		EmploymentDetails empDetails = mapper.map(empDetailsDto, EmploymentDetails.class);
		
		empDetails.setCustomer(cust);
		empDetailsDao.save(empDetails);
		return mapper.map(empDetails, EmploymentDetailsDTO.class);
	}

	@Override
	public EmploymentDetailsDTO getEmployerOfCustomer(Integer custId) {
		EmploymentDetailsDTO empDetDto = mapper.map(empDetailsDao.findById(custId),EmploymentDetailsDTO.class);
		return empDetDto;
	}

	@Override
	public List<EmploymentDetailsDTO> getAllEmployers() {
		List<EmploymentDetails> list = empDetailsDao.findAll();
		return list.stream().map(custIden -> mapper.map(custIden, EmploymentDetailsDTO.class))
				.collect(Collectors.toList());
	}

}
