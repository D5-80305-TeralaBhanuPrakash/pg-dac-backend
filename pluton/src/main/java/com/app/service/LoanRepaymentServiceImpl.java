package com.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.LoanApplicationDao;
import com.app.dao.LoanRepaymentDao;
import com.app.dto.LoanRepaymentDTO;
import com.app.entities.LoanApplication;
import com.app.entities.LoanRepayment;
import com.app.entities.SanctionedLoan;
import com.app.entities.Transaction;

@Service
@Transactional
public class LoanRepaymentServiceImpl implements LoanRepaymentService{

	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	private LoanRepaymentDao loanRepayDao;
	
	@Autowired
	private LoanApplicationDao loanApplDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public LoanRepaymentDTO getLoanRepayDetailsOfApplication(Integer applId) {
		LoanApplication loanAppl = loanApplDao.findById(applId).orElseThrow();
		LoanRepayment loanRepay = loanRepayDao.findByLoanApplication(loanAppl);
		return mapper.map(loanRepay, LoanRepaymentDTO.class);
	}
	
	@Override
	public LoanRepaymentDTO initializeLoanRepayment(SanctionedLoan sancLoan) {
		
		//Creating the object of repayment to insert into the db
        LoanRepayment repayment = new LoanRepayment();
        
        //setting LoanApplication Reference (shared primary key)
        repayment.setLoanApplication(sancLoan.getLoanApplication());
        
        //Initially amount paid will be 0
        repayment.setAmountPaid(BigDecimal.valueOf(0));
        
        //Initially months payment made will be 0
        repayment.setMonthsPaymentMade(0);
        
        //calculating the nextpaymentdate at first it will be the month the loan repayment will be started 
        LocalDate nextPaymentDate = sancLoan.getLoanDisbursementDate().plusMonths(sancLoan.getGracePeriodMonths());
        repayment.setNextPaymentDueDate(nextPaymentDate);
        
        //Calculating the interest amount for one year
        BigDecimal interestRatePerMonth = sancLoan.getInterestRate().divide(BigDecimal.valueOf(12), 5, BigDecimal.ROUND_HALF_UP); // Monthly interest rate
        BigDecimal totalInterestAmount = sancLoan.getAmountDisbursed().multiply(interestRatePerMonth).multiply(BigDecimal.valueOf(sancLoan.getLoanTenureMonths()));
        
        //Adding calculated interest amount to the amount balance that is the loan disbursed
        BigDecimal amountBalance = sancLoan.getAmountDisbursed().add(totalInterestAmount);
        repayment.setAmountBalance(amountBalance);
        
        //Initially months payment made would be the loan tenure
        repayment.setMonthsPaymentPending(sancLoan.getLoanTenureMonths());
        
        //Calculating monthly emi amount
        BigDecimal monthlyEmiAmount = amountBalance.divide(BigDecimal.valueOf(sancLoan.getLoanTenureMonths()));
        repayment.setEmiAmount(monthlyEmiAmount);
        
        // Save the initial repayment entity
        loanRepayDao.save(repayment);
        
        
        return mapper.map(repayment, LoanRepaymentDTO.class);
    }

	@Override
	public LoanRepaymentDTO updateLoanRepayment(Transaction transaction) {
		// Your JPQL query to update loan repayment details
		String jpql = "UPDATE LoanRepayment lr " +
	              "SET lr.amountPaid = lr.amountPaid + :transactionAmount, " +
	              "    lr.amountBalance = lr.amountBalance - :transactionAmount, " +
	              "    lr.monthsPaymentMade = lr.monthsPaymentMade + 1, " +
	              "    lr.monthsPaymentPending = lr.monthsPaymentPending - 1, " +
	              "    lr.nextPaymentDueDate = :nextPaymentDueDate " +
	              "WHERE lr.loanApplication = :loanApplication";

		entityManager.createQuery(jpql)
	             .setParameter("transactionAmount", transaction.getAmount())
	             .setParameter("nextPaymentDueDate", calculateNextPaymentDueDate())
	             .setParameter("loanApplication", transaction.getLoanAppl())
	             .executeUpdate();

		LoanRepayment repayment = loanRepayDao.findById(transaction.getLoanAppl().getLoanApplicationId()).orElseThrow();
		
		return mapper.map(repayment, LoanRepaymentDTO.class);
	}
	
	private LocalDate calculateNextPaymentDueDate() {
        // Calculate the next payment due date based on your business logic
        // For example, you could use the current date and add the loan repayment period
        return LocalDate.now().plusMonths(1); // Assuming monthly payments
    }


}
