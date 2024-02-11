package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.AddressNotFoundException;
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


	@Override
	public List<AddressDTO> getAllAddresses() {
		List<Address> adrlist = adrDao.findAll();
		
		return adrlist.stream()
				.map(adr -> mapper.map(adr, AddressDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public AddressDTO getCustAddress(Integer custId) {
		Address adr = adrDao.findById(custId).orElseThrow(()->new AddressNotFoundException("address not found"));
		return mapper.map(adr, AddressDTO.class);
	}

}
