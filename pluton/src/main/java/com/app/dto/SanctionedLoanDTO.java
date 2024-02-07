package com.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SanctionedLoanDTO {
	//private LoanApplication loanApplication;

    
    private Integer loanTenureMonths;

    
    private BigDecimal amountDisbursed;


    private BigDecimal interestRate;


    private BigDecimal latePaymentPenalty;


    private Integer gracePeriodMonths;


    private LocalDate loanDisbursementDate;


    private LocalDate loanClosureDate;

    
    private BigDecimal prepaymentPenalty;

    
    private String loanStatus;

}
