package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CustomerDao;
import com.app.dao.CustomerIdentificationDao;
import com.app.dto.CustomerIdentificationDTO;
import com.app.entities.Customer;
import com.app.entities.CustomerIdentification;

@Service
@Transactional
public class CustomerIdentificationServiceImpl implements CustomerIdentificationService{

	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private CustomerIdentificationDao custIdenDao;
	
	@Autowired
	private ModelMapper mapper;
		
	@Override
	public CustomerIdentificationDTO addIdentificationToCustomer(Integer custId,
			CustomerIdentificationDTO custIdentDto) {
		Customer cust = custDao.findById(custId).orElseThrow();
		
		System.out.println(cust);
		
		CustomerIdentification custIden = mapper.map(custIdentDto, CustomerIdentification.class);
		custIden.setCustomer(cust);
		custIdenDao.save(custIden);
		return mapper.map(custIden, CustomerIdentificationDTO.class);
	}

}
