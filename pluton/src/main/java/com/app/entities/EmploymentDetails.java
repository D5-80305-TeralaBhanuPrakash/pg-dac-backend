package com.app.entities;

import java.math.BigDecimal;

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
@Table(name = "employment_details")
public class EmploymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employmentId")
    private Integer employmentId;

    @OneToOne
    @JoinColumn(name = "customerId")
    @MapsId
    private Customer customer;

    @Column(name = "employerName", nullable = false)
    private String employerName;

    @Column(name = "employmentStatus", nullable = false)
    private String employmentStatus;

    @Column(name = "jobTitle", nullable = false)
    private String jobTitle;

    @Column(name = "ctc", nullable = false)
    private BigDecimal ctc;

    @Column(name = "monthlyIncome", nullable = false)
    private BigDecimal monthlyIncome;

    // Add getters and setters
}


