package com.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SanctionedLoanDTO {
	//private LoanApplication loanApplication;
	@JsonProperty(access = Access.READ_ONLY)
	private Integer loanDetailsId;
    
    private Integer loanTenureMonths;

    
    private BigDecimal amountDisbursed;


    private BigDecimal interestRate;


    private BigDecimal latePaymentPenalty;


    private Integer gracePeriodMonths;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDate loanDisbursementDate;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDate loanClosureDate;

    
    private BigDecimal prepaymentPenalty;

    @JsonProperty(access = Access.READ_ONLY)
    private String loanStatus;

}
