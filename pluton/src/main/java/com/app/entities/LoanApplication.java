package com.app.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Loan_Application")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanApplicationId")
    private Integer loanApplicationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId",nullable=false)
    private Customer customer;

    @Column(name = "loanType")
    private String loanType;

    @Column(name = "requestedAmount")
    private BigDecimal requestedAmount;

    @Column(name = "interestRate")
    private BigDecimal interestRate;

    @Column(name = "loanTerm")
    private Integer loanTerm;

    @Column(name = "applicationDate")
    private LocalDate applicationDate;

    @Column(name = "approvalStatus")
    private String approvalStatus;

    @Column(name = "approvalDate")
    private LocalDate approvalDate;

    @Column(name = "denialReason")
    private String denialReason;

    @Lazy
    @OneToOne
    @JoinColumn(name = "employmentId")
    private EmploymentDetails employmentDetails;

    @Column(name = "creditScore")
    private Integer creditScore;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "applicationStatus")
    private String applicationStatus;

    // Constructors, getters, and setters
}

