package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.LoanApplicationDao;
import com.app.dao.SanctionedLoanDao;
import com.app.dto.SanctionedLoanDTO;
import com.app.entities.LoanApplication;
import com.app.entities.SanctionedLoan;

@Service
@Transactional
public class SanctionedLoanServiceImpl implements SanctionedLoanService{

	@Autowired
	private SanctionedLoanDao sancDao;
	
	@Autowired
	private LoanApplicationDao applDao;
	
	@Autowired
	private LoanRepaymentService loanRepayService;
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SanctionedLoanDTO addSactionedLoan(Integer applId, SanctionedLoanDTO sancLoanDto) {
		LoanApplication loanAppl = applDao.findById(applId).orElseThrow();
		
		SanctionedLoan sancLoan = mapper.map(sancLoanDto, SanctionedLoan.class);
		//updating all field of loan Application
		loanAppl.setApplicationStatus("ACTIVE");
		loanAppl.setApprovalStatus("APPROVED");
		loanAppl.setApprovalDate(LocalDate.now());
		loanAppl.setLoanTerm(sancLoan.getLoanTenureMonths());
		
		//setting all the fields of sanctioned loan
		sancLoan.setLoanApplication(loanAppl);
		sancLoan.setLoanDisbursementDate(LocalDate.now());
		sancLoan.setLoanClosureDate(LocalDate.now().plusMonths(sancLoan.getLoanTenureMonths()+sancLoan.getGracePeriodMonths()));
		sancLoan.setLoanStatus("ACTIVE");
		
		loanRepayService.initializeLoanRepayment(sancLoan);
		sancDao.save(sancLoan);
		return mapper.map(sancLoan, SanctionedLoanDTO.class);
	}

	@Override
	public List<SanctionedLoanDTO> getSanctionedLoanOfCustomer(Integer custId) {
		List<SanctionedLoan> sancLoansOfCustomer = sancDao.getSanctionedLoanOfCustomer(custId);
		
		return sancLoansOfCustomer
				.stream().map(sancLoan -> mapper
				.map(sancLoan, SanctionedLoanDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public List<SanctionedLoanDTO> sortByAmountDisbursed(Integer customerId) {
		List<SanctionedLoan> sancLoansOfCustomer = sancDao.findByLoanApplicationCustomerCustomerIdOrderByAmountDisbursed(customerId);
		
		return sancLoansOfCustomer
				.stream().map(sancLoan -> mapper
				.map(sancLoan, SanctionedLoanDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<SanctionedLoanDTO> getAllSanctionedLoans() {
		List<SanctionedLoan> sancLoans = sancDao.findAll();
		return sancLoans
				.stream().map(sancLoan -> mapper
				.map(sancLoan, SanctionedLoanDTO.class))
				.collect(Collectors.toList());
	}
	
	public List<SanctionedLoanDTO> getAllSanctionedLoans(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanctionedLoan> pagedResult = sancDao.findAll(pageable);
        return pagedResult.getContent()
                .stream()
                .map(sancLoan -> mapper.map(sancLoan, SanctionedLoanDTO.class))
                .collect(Collectors.toList());
    }

	@Override
	public SanctionedLoanDTO updateSanctionedLoan(Integer applId, SanctionedLoanDTO sancLoanDto) {
		SanctionedLoan sancLoan = sancDao.findById(applId).orElseThrow();
		mapper.map(sancLoanDto, sancLoan);
		sancLoan.setLoanDetailsId(applId);
		
		
		return mapper.map(sancDao.save(sancLoan), SanctionedLoanDTO.class);
	}
	
}
