package com.app.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class EmploymentDetailsDTO {
	
	private String employerName;

    
    private String employmentStatus;

    
    private String jobTitle;

    
    private BigDecimal ctc;

    
    private BigDecimal monthlyIncome;
}
