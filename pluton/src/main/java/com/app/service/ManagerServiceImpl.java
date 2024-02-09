package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ManagerDao;
import com.app.dto.ManagerDTO;
import com.app.entities.Manager;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerDao managDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ManagerDTO getManagerDetails(Integer managId) {
		Manager manag = managDao.findById(managId).orElseThrow();
		return mapper.map(manag,ManagerDTO.class);
	}
	

	@Override
	public ManagerDTO loginManager(String email, String password) {
		Manager manag = managDao.findManagerByEmailAndPassword(email,password);
		return mapper.map(manag,ManagerDTO.class);
	}

	@Override
	public ManagerDTO addManager(ManagerDTO managDto) {
		Manager manag = managDao.save(mapper.map(managDto, Manager.class));
		
		return mapper.map(manag,ManagerDTO.class);
	}
}
