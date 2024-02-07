package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AddressDao;
import com.app.dao.CustomerDao;
import com.app.dto.AddressDTO;
import com.app.entities.Address;
import com.app.entities.Customer;


@Service
@Transactional
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressDao adrDao;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public AddressDTO addAddressToCustomer(Integer custId, AddressDTO adrDto) {
		Customer cust = custDao.findById(custId).orElseThrow();
		
		System.out.println(cust);
		Address adr = mapper.map(adrDto, Address.class);
		
		//if set customer after the save there will be error and the cust
		//variable become null so first converted from dto to pojo and then set the
		//cust object into that pojo then after saved the address follow this for every
		//service to avoid the error
		adr.setCustomer(cust);
		adrDao.save(adr);
		
		return mapper.map(adr, AddressDTO.class);

	}

}
