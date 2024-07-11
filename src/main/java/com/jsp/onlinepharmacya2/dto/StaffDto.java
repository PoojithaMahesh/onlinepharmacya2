package com.jsp.onlinepharmacya2.dto;

import com.jsp.onlinepharmacya2.entity.Admin;
import com.jsp.onlinepharmacya2.entity.MedicalStore;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDto {
	private int staffId;
	private String staffName;
	private String staffEmail;
	@ManyToOne
	private AdminDto adminDto;
	@OneToOne
	private MedicalStoreDto medicalStoreDto;
}
