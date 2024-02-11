package com.app.service;

import java.util.List;

import com.app.dto.AddressDTO;

public interface AddressService {

	AddressDTO addAddressToCustomer(Integer custId, AddressDTO adrDto);

	List<AddressDTO> getAllAddresses();

	AddressDTO getCustAddress(Integer custId);
	
}
