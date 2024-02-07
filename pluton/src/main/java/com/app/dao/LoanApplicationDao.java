package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.LoanApplication;

public interface LoanApplicationDao extends JpaRepository<LoanApplication, Integer> {

}
