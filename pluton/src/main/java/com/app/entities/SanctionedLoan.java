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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanctionedLoan")
public class SanctionedLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanDetailsId")
    private Integer loanDetailsId;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="loanApplicationId")
    @MapsId
    private LoanApplication loanApplication;

    @Column(name = "loanTenureMonths")
    private Integer loanTenureMonths;

    @Column(name = "amountDisbursed")
    private BigDecimal amountDisbursed;

    @Column(name = "interestRate")
    private BigDecimal interestRate;

    @Column(name = "latePaymentPenalty")
    private BigDecimal latePaymentPenalty;

    @Column(name = "gracePeriodMonths")
    private Integer gracePeriodMonths;

    @Column(name = "loanDisbursementDate")
    private LocalDate loanDisbursementDate;

    @Column(name = "loanClosureDate")
    private LocalDate loanClosureDate;

    @Column(name = "prepaymentPenalty")
    private BigDecimal prepaymentPenalty;

    @Column(name = "loanStatus")
    private String loanStatus;

    // Constructors, getters, and setters
}

