package com.app.entities;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer_identification")
public class CustomerIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificationId")
    private Integer identificationId;

    @OneToOne
    @JoinColumn(name = "customerId",nullable=false)
    @MapsId
    private Customer customer;

    @Column(name = "aadhaarCard", nullable = false,unique=true)
    private String aadhaarCard;

    @Column(name = "panCard", nullable = false,unique=true)
    private String panCard;

    @Column(name = "passport",unique=true)
    private String passport;

    @Column(name = "drivingLicenseNo", nullable = false,unique=true)
    private String drivingLicenseNo;

    // Add getters and setters
}
