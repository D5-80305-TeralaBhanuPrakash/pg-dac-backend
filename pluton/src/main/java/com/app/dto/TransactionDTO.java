package com.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class TransactionDTO {
	private String transactionType;

    @Column(name = "transactionDate")
    private LocalDateTime transactionDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "merchantType")
    private String merchantType;

    @Column(name = "transactionCategory")
    private String transactionCategory;
}
