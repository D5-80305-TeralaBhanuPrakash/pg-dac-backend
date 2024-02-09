package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Manager;

public interface ManagerDao extends JpaRepository<Manager,Integer>{
	
	@Query("select m from Manager m where m.email=?1 and m.password=?2")
	Manager findManagerByEmailAndPassword(String email, String password);
}
