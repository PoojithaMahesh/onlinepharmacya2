package com.jsp.onlinepharmacya2.dto;

import com.jsp.onlinepharmacya2.entity.Address;
import com.jsp.onlinepharmacya2.entity.Admin;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MedicalStoreDto {
	private int storeId;
	private String name;
	private String managerName;
	private long phone;
	
	@ManyToOne
	private AdminDto  adminDto;
	
	@OneToOne
	private AddressDto addressDto;
}
