package com.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    
    @ManyToMany // mandatory
	// optional BUT recommended
	@JoinTable(name = "transaction_loan", joinColumns = @JoinColumn(name = "transaction_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "loan_application_id", nullable = false))
    private List<LoanApplication> applications = new ArrayList<>();
    
    @Column(name = "transactionType")
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
    
    
    //helper methods
    public void addLoanApplication(LoanApplication loanAppl) {
    	applications.add(loanAppl);
	}
    
    public void removeEmployee(LoanApplication loanAppl) {
    	applications.remove(loanAppl);
	}
    
    
    
    
    
    
}