package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.LoanApplication;
import com.app.entities.LoanRepayment;

public interface LoanRepaymentDao extends JpaRepository<LoanRepayment,Integer>{
	LoanRepayment findByLoanApplication(LoanApplication appl);
}
