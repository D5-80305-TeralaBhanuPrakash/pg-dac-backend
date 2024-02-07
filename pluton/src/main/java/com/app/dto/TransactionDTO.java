package com.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class TransactionDTO {
	private String transactionType;

    
    private LocalDateTime transactionDate;

    
    private BigDecimal amount;

    
    private String description;

    
    private String merchantType;

    
    private String transactionCategory;
}
