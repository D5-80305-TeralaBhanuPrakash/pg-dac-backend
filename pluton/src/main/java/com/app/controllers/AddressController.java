package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddressDTO;
import com.app.service.AddressService;

@RestController
@RequestMapping("/customer/address")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class AddressController {
		
	@Autowired
	private AddressService adrService;
	
	
	@PostMapping("/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public AddressDTO addAddressToCustomer(@PathVariable Integer custId, @RequestBody AddressDTO adrDto) {
		return adrService.addAddressToCustomer(custId,adrDto);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<AddressDTO> getAllAddresses(){
		return adrService.getAllAddresses();
	}
	
	@GetMapping("get/{custId}")
	@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
	public AddressDTO getCustAddress(@PathVariable Integer custId) {
		return adrService.getCustAddress(custId);
	}
}
