package com.jsp.onlinepharmacya2.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
	
	@ManyToOne
	private CustomerDto customerDto;
	
	@OneToOne
	private MedicalStoreDto medicalStoreDto;
}
