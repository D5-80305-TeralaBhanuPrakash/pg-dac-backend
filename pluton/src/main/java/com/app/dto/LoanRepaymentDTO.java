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
public class LoanRepaymentDTO {
	
	
	private BigDecimal amountPaid;

	
    private BigDecimal amountBalance;

    
    private Integer monthsPaymentMade;

    
    private Integer monthsPaymentPending;

    
    private LocalDate nextPaymentDueDate;
    
    private BigDecimal emiAmount;
}
