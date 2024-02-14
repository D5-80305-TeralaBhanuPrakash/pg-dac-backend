package com.app.service;

import java.util.List;

import com.app.dto.EmploymentDetailsDTO;

public interface EmploymentDetailsService {

	EmploymentDetailsDTO addEmployerToCustomer(Integer custId, EmploymentDetailsDTO empDetailsDto);

	EmploymentDetailsDTO getEmployerOfCustomer(Integer custId);

	List<EmploymentDetailsDTO> getAllEmployers();

}
