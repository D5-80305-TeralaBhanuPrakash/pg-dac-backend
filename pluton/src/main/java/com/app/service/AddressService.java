package com.app.service;

import com.app.dto.AddressDTO;

public interface AddressService {

	AddressDTO addAddressToCustomer(Integer custId, AddressDTO adrDto);
	
}
