package com.app.service;

import com.app.dto.ManagerDTO;

public interface ManagerService {

	ManagerDTO getManagerDetails(Integer managId);

	ManagerDTO loginManager(String email, String password);

	ManagerDTO addManager(ManagerDTO managDto);

	

}
