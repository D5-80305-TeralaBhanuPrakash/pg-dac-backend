package com.app.service;

import java.util.List;

import com.app.dto.CustomerIdentificationDTO;

public interface CustomerIdentificationService {

	CustomerIdentificationDTO addIdentificationToCustomer(Integer custId, CustomerIdentificationDTO custIdentDto);

	CustomerIdentificationDTO getCustomerIdentification(Integer custId);

	List<CustomerIdentificationDTO> getAllCustomerIdentification();

}
