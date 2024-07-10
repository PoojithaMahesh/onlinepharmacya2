package com.jsp.onlinepharmacya2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@OneToOne
	@JoinColumn
	private MedicalStore medicalStore;
	
}
