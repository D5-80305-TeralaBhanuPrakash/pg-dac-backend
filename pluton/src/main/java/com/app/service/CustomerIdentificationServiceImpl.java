package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.CustomerNotFoundException;
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
		Customer cust = custDao.findById(custId).orElseThrow(()->new CustomerNotFoundException("customer not found"));
		
		System.out.println(cust);
		
		CustomerIdentification custIden = mapper.map(custIdentDto, CustomerIdentification.class);
		custIden.setCustomer(cust);
		custIdenDao.save(custIden);
		return mapper.map(custIden, CustomerIdentificationDTO.class);
	}

	@Override
	public CustomerIdentificationDTO getCustomerIdentification(Integer custId) {
		CustomerIdentificationDTO custIdenDto = mapper.map(custIdenDao.findById(custId),CustomerIdentificationDTO.class);
		return custIdenDto;
	}

	@Override
	public List<CustomerIdentificationDTO> getAllCustomerIdentification() {
		List<CustomerIdentification> list = custIdenDao.findAll();
		return list.stream().map(custIden -> mapper.map(custIden, CustomerIdentificationDTO.class))
				.collect(Collectors.toList());
	}

}
