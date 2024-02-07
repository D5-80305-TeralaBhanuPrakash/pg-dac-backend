package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.SanctionedLoan;

public interface SanctionedLoanDao extends JpaRepository<SanctionedLoan,Integer>{

}
