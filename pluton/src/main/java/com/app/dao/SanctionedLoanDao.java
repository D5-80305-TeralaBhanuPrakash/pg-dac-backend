package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.SanctionedLoan;

public interface SanctionedLoanDao extends JpaRepository<SanctionedLoan,Integer>{

	@Query("SELECT sl FROM SanctionedLoan sl JOIN sl.loanApplication la JOIN la.customer c WHERE c.customerId = :custId")
	List<SanctionedLoan> getSanctionedLoanOfCustomer(Integer custId);
	//List<SanctionedLoan> findByLoanApplicationCustomerCustomerId(Integer custId);
	
	List<SanctionedLoan> findByLoanApplicationCustomerCustomerIdOrderByAmountDisbursed(Integer customerId);
	
	
	Page<SanctionedLoan> findAll(Pageable pageable);
}
