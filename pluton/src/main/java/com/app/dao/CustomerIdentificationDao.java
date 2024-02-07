package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CustomerIdentification;

public interface CustomerIdentificationDao extends JpaRepository<CustomerIdentification,Integer>{

}
