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
public class LoanApplicationDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer customerId;
	
	private String loanType;

    
    private BigDecimal requestedAmount;

    
    private BigDecimal interestRate;

   
    private Integer loanTerm;

    
    private LocalDate applicationDate;

    
    private String approvalStatus;

    
    private LocalDate approvalDate;

    
    private String denialReason;

    
    private Integer creditScore;

    
    private String comments;

    
    private String applicationStatus;
    
    
//    private Integer customer;
//    
//    //Employement details association
//    private Integer employmentDetails;
}
