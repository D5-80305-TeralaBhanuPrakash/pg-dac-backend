package com.app.service;

import com.app.dto.CustomerIdentificationDTO;

public interface CustomerIdentificationService {

	CustomerIdentificationDTO addIdentificationToCustomer(Integer custId, CustomerIdentificationDTO custIdentDto);

}
