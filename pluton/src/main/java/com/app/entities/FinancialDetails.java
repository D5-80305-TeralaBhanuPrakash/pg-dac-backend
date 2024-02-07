package com.app.entities;

import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "financial_details")
public class FinancialDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "financialID")
    private Integer financialID;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @Column(name = "income", nullable = false)
    private BigDecimal income;

    @Column(name = "creditScore", nullable = false)
    private Integer creditScore;

    // Add getters and setters
}

