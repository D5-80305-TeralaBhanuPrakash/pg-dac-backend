package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CustomerDao;
import com.app.dto.CustomerDTO;
import com.app.entities.Customer;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return custDao.findAll();
	}

	@Override
	public CustomerDTO addCustomer(CustomerDTO custDto) {
		Customer cust = custDao.save(mapper.map(custDto, Customer.class));
		return mapper.map(cust, CustomerDTO.class);
	}

}
