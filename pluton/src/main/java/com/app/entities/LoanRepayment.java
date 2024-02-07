package com.app.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LoanRepayment")
public class LoanRepayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanRepaymentId")
    private Integer loanRepaymentId;

    @OneToOne(mappedBy = "loanRepayment")
    private LoanApplication loanApplication;

    @Column(name = "amountPaid")
    private BigDecimal amountPaid;

    @Column(name = "amountBalance")
    private BigDecimal amountBalance;

    @Column(name = "monthsPaymentMade")
    private Integer monthsPaymentMade;

    @Column(name = "monthsPaymentPending")
    private Integer monthsPaymentPending;

    @Column(name = "nextPaymentDueDate")
    private LocalDate nextPaymentDueDate;

    // Constructors, getters, and setters
}

