package com.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TransactionDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer transactionId;
	
	private String transactionType;

    
    private LocalDateTime transactionDate;

    
    private BigDecimal amount;

    
    private String description;

    
    private String merchantType;

    
    private String transactionCategory;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer applicationId;
}
