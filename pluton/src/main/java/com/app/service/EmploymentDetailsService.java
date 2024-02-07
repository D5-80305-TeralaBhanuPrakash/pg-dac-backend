package com.app.service;

import com.app.dto.EmploymentDetailsDTO;

public interface EmploymentDetailsService {

	EmploymentDetailsDTO addEmployerToCustomer(Integer custId, EmploymentDetailsDTO empDetailsDto);

}
